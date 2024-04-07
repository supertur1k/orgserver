package com.medicine.orgserver.services;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.medicine.orgserver.dto.Medicine;
import com.medicine.orgserver.getInfoByBarCode.DateDeserializer;
import com.medicine.orgserver.repositories.MedicamentRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

@Component
@RequiredArgsConstructor
@Slf4j
public class RLSService {
    static final String token = "Basic YXJ0dXJ6YW1pbG92MjAwMToyNzgxNDM2ODYz";

    private final MedicamentRepository medicamentRepository;

    @PostConstruct
    public void init() {
        getDataFromRLS();
    }

    public void getDataFromRLS() {
        try {
            URL url = new URL("https://rls-aurora.ru/api/inventory_complete");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Authorization", token);
            conn.setRequestProperty("Accept", "application/json");

            InputStream inputStream = conn.getInputStream();
            ZipInputStream zipInputStream = new ZipInputStream(inputStream);
            ZipEntry entry;
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

            while ((entry = zipInputStream.getNextEntry()) != null) {
                String name = entry.getName();
                int size;
                byte[] buffer = new byte[2048];
                while ((size = zipInputStream.read(buffer, 0, buffer.length)) != -1) {
                    outputStream.write(buffer, 0, size);
                }
            }

            String fileContent = outputStream.toString("UTF-8");
            List<String> packing_ids = new ArrayList<>(Arrays.stream(fileContent.split("\\{")).toList());

            packing_ids.remove(0);

            for (int i = 0; i < packing_ids.size(); i++) {
                if (packing_ids.get(i).startsWith("\"packing_id\"")) {
                    packing_ids.set(i, "{" + packing_ids.get(i));
                    packing_ids.set(i, packing_ids.get(i).substring(0, packing_ids.get(i).length()-1));
                }
            }
            outputStream.close();


            List<Medicine> listOfMedicamentsFromRLS = new ArrayList<>();

            for (int i = 0; i < packing_ids.size(); i++) {
                Medicine medicine = null;
                try {
                    GsonBuilder gsonBuilder = new GsonBuilder();
                    // Регистрируем кастомный десериализатор для поля с датой
                    gsonBuilder.registerTypeAdapter(Date.class, new DateDeserializer());
                    // Инициализируем объект Gson с настройками
                    Gson gson = gsonBuilder.create();
                    // Преобразуем JSON в объект класса Medicine
                    medicine = gson.fromJson(packing_ids.get(i), Medicine.class);
                } catch (Exception | Error ignored) {};
                if (medicine != null) listOfMedicamentsFromRLS.add(medicine);
            }

            List<Medicine> medicines = listOfMedicamentsFromRLS.stream().filter(x -> x.getBarcode() != null && !x.getBarcode().isBlank())
                    .map(x -> {
                        if (x.getBarcode().startsWith("0")) {
                            x.setBarcode(x.getBarcode().substring(1));
                        }
                        return x;
                    })
                    .filter(x->x.getScName() != null && x.getAmount() != null && x.getTradeNameRusHtml() != null)
                    .toList();
            //todo добавить все данные из рлс в базу
        } catch (Exception | Error ignored) {
            ignored.printStackTrace();
        };

    }

}
