package com.library.management.order;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.commons.codec.binary.Base64;

import com.library.management.domain.Book;
import com.library.management.domain.Order;

public class OrderDownloadHandler extends OrderHandler {


	public OrderDownloadHandler(OrderHandler orderHandler) {
		super(orderHandler);
	}

	@Override
	public String processOrder(Order order) {
		
		byte[] zipContent = createZipArchive(order.getBooks());
		return  Base64.encodeBase64String(zipContent);
		
	}
	
	private byte[] createZipArchive(List<Book> books) {

		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ZipOutputStream zipOut = new ZipOutputStream(baos);

			books.forEach(book -> {
				byte[] pdfContent = loadPdfContent(book.getPdfUrl());
				if (pdfContent != null) {
					try {
						ZipEntry entry = new ZipEntry(book.getBookname() + ".pdf");
						zipOut.putNextEntry(entry);
						zipOut.write(pdfContent);
						zipOut.closeEntry();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			});
			zipOut.close();
			return baos.toByteArray();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	private byte[] loadPdfContent(String pdfFilePath) {

		try {
			Path path = Paths.get(pdfFilePath);
			return Files.readAllBytes(path);
		} catch (IOException e) {
			e.printStackTrace();
			return null; 
		}
	}

}
