package com.wx.demo;


import com.aspose.pdf.SaveFormat;
import com.aspose.words.Document;

import java.io.IOException;

public class Main2 {
    public static void main(String[] args) throws IOException {
//        ConvertPDFtoWord();
//        ConvertPDFtoWordDocAdvanced();
//        ConvertPDFtoHTML_SplittingOutput();
        try {
            ConvertWordToFDF();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }



    public static void ConvertWordToFDF() throws Exception {
        // Open the source PDF document
        String _dataDir = "C:\\Users\\y'y\\Desktop\\资料\\go\\";
        Document pdfDocument = new Document(_dataDir + "PDF-to-DOC.doc");
        pdfDocument.save(_dataDir + "out.pdf");
    }



}
