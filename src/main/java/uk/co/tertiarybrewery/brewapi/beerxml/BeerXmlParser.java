package uk.co.tertiarybrewery.brewapi.beerxml;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.IOException;
import java.io.InputStream;

@Component
public class BeerXmlParser {


    DocumentBuilderFactory documentBuilderFactory;
    DocumentBuilder documentBuilder;

    public BeerXmlParser() throws ParserConfigurationException {
        documentBuilderFactory  = DocumentBuilderFactory.newInstance();
        documentBuilder = documentBuilderFactory.newDocumentBuilder();

    }


    public void parse(MultipartFile file) throws BeerXmlParseException, BeerXMLReadException {
        InputStream xmlStream;
        Document dom;
        NodeList nodes;
        XPath xPath = XPathFactory.newInstance().newXPath();
        try {
            xmlStream = file.getInputStream();
        } catch (IOException e) {
            throw new BeerXMLReadException("IO Issue");
        }
        try {
            dom = documentBuilder.parse(xmlStream);
        } catch (SAXException e) {
            throw new BeerXmlParseException("Invalid XML");
        }
        catch (IOException e) {
            throw new BeerXMLReadException("IO Issue");
        }
        try {
            nodes = (NodeList)xPath.evaluate("/RECIPES/RECIPE/MASH/MASH_STEPS",
                    dom, XPathConstants.NODESET);
        } catch (XPathExpressionException e) {
            throw new BeerXmlParseException("Unable to find mash steps");
        }
        for (int i = 0; i < nodes.getLength(); ++i) {
            Element e = (Element) nodes.item(i);
        }
    }


}
