package com.medicine.orgserver;

import com.medicine.orgserver.dto.RLSxmlDto;
import org.checkerframework.common.value.qual.IntRange;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toCollection;

class OrgserverApplicationTests {

	@Test
	void contextLoads() throws IOException, SAXException, ParserConfigurationException {
		File file = new File("src/main/resources/rlsData/aurora_inventory_brief_20240301.xml");
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory
				.newInstance();
		DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
		Document document = documentBuilder.parse(file);
		NodeList nodeList = document.getElementsByTagName("prep_short");
		var s = nodeList.getLength();

		ArrayList<String> prep_shorts = IntStream.range(0,nodeList.getLength()).mapToObj(
				x -> document.getElementsByTagName("prep_short").item(x).getTextContent()).collect(toCollection(ArrayList::new)
				);
		String c = prep_shorts.stream().filter(x->x.contains("ЦИТРАМОН")).findFirst().get();
		String usr = document.getElementsByTagName("prep_short").item(0).getTextContent();
		//String pwd = document.getElementsByTagName("password").item(0).getTextContent();
	}

}
