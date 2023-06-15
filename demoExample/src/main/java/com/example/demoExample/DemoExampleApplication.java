package com.example.demoExample;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.example.demoExample.entity.UserEntity;
import com.example.demoExample.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootApplication
@RestController
public class DemoExampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoExampleApplication.class, args);
	}
	
	@Autowired
	public UserService userService; 
	
	@GetMapping("/userdetails")
	public List<UserEntity>  getDetails() {		
		List<UserEntity> list = userService.getAllUsers();
		return list;
	}
	
	@PostMapping("/postuserdetails")
	public UserEntity postDetails(@RequestBody UserEntity user) throws JsonProcessingException,
	ParserConfigurationException ,IOException,TransformerException{
		  UserEntity u =this.userService.addUsers(user);
	
		  ObjectMapper objectMapper = new ObjectMapper();
		  String c = objectMapper.writeValueAsString(u);
	         
		  JsonNode jsonNode = objectMapper.readTree(c);
//		  JsonNode userNode= jsonNode.get("userdetails");
		
		  DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		  DocumentBuilder builder = factory.newDocumentBuilder();
   
	      try {
	    	    builder = factory.newDocumentBuilder();
	      }catch (ParserConfigurationException e) {
	    	    throw new IOException("Failed to create settings document builder", e);
	      }
	      Document doc = builder.newDocument();
	    	  //System.out.println(userNode);
	    	  System.out.println(jsonNode);
	      
	      Element root = doc.createElement("root");
	      doc.appendChild(root);
    
	      Element userId =doc.createElement("userId");
	      userId.setTextContent(jsonNode.get("userId").asText());
	      root.appendChild(userId);

	      Element id =doc.createElement("id");
	      id.setTextContent(jsonNode.get("id").asText());
	      root.appendChild(id);
      
	      Element name =doc.createElement("name");
	      name.setTextContent(jsonNode.get("name").asText());
	      root.appendChild(name);
	      
	      Element age =doc.createElement("age");
	      age.setTextContent(jsonNode.get("age").asText());
	      root.appendChild(age);
	      	    
	      TransformerFactory transformerFactory = TransformerFactory.newInstance();
	      Transformer transf = transformerFactory.newTransformer();

	      transf.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
	      transf.setOutputProperty(OutputKeys.INDENT, "yes");
	      transf.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");

	      DOMSource source = new DOMSource(doc);

	      File myFile = new File("src/main/resources/users.xml");

	      StreamResult console = new StreamResult(System.out);
	      StreamResult file = new StreamResult(myFile);

	      transf.transform(source, console);
	      transf.transform(source, file);
	  	
	      return u;
	  
}


}
