package com.example.demo.controller;
import com.example.demo.entity.Rental;
import com.example.demo.service.RentalService;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import jakarta.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.IOException;
import java.io.OutputStream;


@Controller
public class PdfController {
	
	@Autowired
	private RentalService  rentalService;
	
	 @GetMapping("/manager/generatePDF/{id}")
	    public void generatePDF(@PathVariable int id, HttpServletResponse response) {
	        // Fetch the rental details based on the ID (You need to implement this part)
	        Rental rental = rentalService.getRentalById(id);

	        // Set the response content type
	        response.setContentType("application/pdf");

	        // Set the header for the response
	        response.setHeader("Content-Disposition", "inline; filename=RentalInvoice.pdf");

	        try (OutputStream outputStream = response.getOutputStream()) {
	            // Create a new Document
	            Document document = new Document();

	            // Write PDF content
	            PdfWriter.getInstance(document, outputStream);
	            document.open();
	            Font headingFont = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD);
	            Paragraph heading = new Paragraph("GOCAR" ,headingFont );
	            heading.setAlignment(Element.ALIGN_CENTER); // or Element.ALIGN_RIGHT
	            document.add(heading);
	            document.add(Chunk.NEWLINE);
	            

	            // Add rental details to the PDF
	            
	            document.add(new Paragraph("Contract ID: " + rental.getContract().getId()));
	            document.add(Chunk.NEWLINE);
	            document.add(new Paragraph("Rental ID: " + rental.getRentalId()));
	            document.add(Chunk.NEWLINE);
	            document.add(new Paragraph("Client Name: " + rental.getClientFullName()));
	            document.add(Chunk.NEWLINE);
	            document.add(new Paragraph("CIN: " + rental.getCIN()));
	            document.add(Chunk.NEWLINE);
	            document.add(new Paragraph("Phone Number: " + rental.getPhone()));
	            document.add(Chunk.NEWLINE);
	            document.add(new Paragraph("Total Price: " + rental.getTotalPrice()));
	            document.add(Chunk.NEWLINE);
	            document.add(new Paragraph("Start date: " + rental.getReservationStartDate()));
	            document.add(Chunk.NEWLINE);
	            document.add(new Paragraph("End date: " + rental.getReservationEndDate()));
	            document.add(Chunk.NEWLINE);
	            document.add(new Paragraph("Car model: " + rental.getCar().getModel()));
	            document.add(Chunk.NEWLINE);
	            document.add(new Paragraph("Matricule: " + rental.getCar().getMatricule()));


	      

	            document.close();
	        } catch (DocumentException | IOException e) {
	            // Handle exceptions
	            e.printStackTrace();
	        }
	    }
}
