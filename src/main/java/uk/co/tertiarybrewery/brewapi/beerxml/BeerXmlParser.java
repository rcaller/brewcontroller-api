package uk.co.tertiarybrewery.brewapi.beerxml;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import uk.co.tertiarybrewery.brewapi.mashprofile.MashProfileBuilder;
import uk.co.tertiarybrewery.brewapi.tempdata.TempsDataService;

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

    @Autowired
    MashProfileBuilder mashProfileBuilder;

    @Autowired
    TempsDataService tempsDataService;

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
            nodes = (NodeList)xPath.evaluate("/RECIPES/RECIPE/MASH/MASH_STEPS/MASH_STEP",
                    dom, XPathConstants.NODESET);
        } catch (XPathExpressionException e) {
            throw new BeerXmlParseException("Unable to find mash steps");
        }
        mashProfileBuilder.clearProfile();
        tempsDataService.clear();

        for (int i = 0; i < nodes.getLength(); i++) {
            Node stepDetails = nodes.item(i);
            Float stepTime= getNodeValue(xPath, stepDetails,"STEP_TIME");
            Float stepTemp= getNodeValue(xPath, stepDetails,"STEP_TEMP");
            Float rampTime= getNodeValue(xPath, stepDetails,"RAMP_TIME");
            Float infusionTemp = getNodeValue(xPath, stepDetails,"INFUSE_TEMP");
            mashProfileBuilder.addStep(stepTime, stepTemp, rampTime, infusionTemp);

        }
    }

    private Float getNodeValue(XPath xPath, Node stepDetails, String nodeName) throws BeerXmlParseException {
        Float dataValue;
        try {
           
            dataValue = Float.valueOf(xPath.evaluate(nodeName, stepDetails));
        } catch (XPathExpressionException e) {
            throw new BeerXmlParseException("Invalid XML (STEP_TIME)");
        } catch (NumberFormatException e) {
            dataValue=0f;
        }
        return dataValue;
    }


}
