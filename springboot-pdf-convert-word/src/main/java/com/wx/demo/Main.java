package com.wx.demo;

import com.aspose.pdf.DocSaveOptions;
import com.aspose.pdf.Document;
import com.aspose.pdf.HtmlSaveOptions;
import com.aspose.pdf.SaveFormat;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) throws IOException {
//        ConvertPDFtoWord();
//        ConvertPDFtoWordDocAdvanced();
        ConvertPDFtoHTML_SplittingOutput();
//        ConvertWordToFDF();
    }

    public static void ConvertPDFtoWord() {
        // Open the source PDF document
        String _dataDir = "C:\\Users\\yy\\Desktop\\资料/mysql/";
        Document pdfDocument = new Document(_dataDir + "OReilly.High.Performance.MySQL.4th.Edition.2021.11.pdf");
        // Save the file into MS document format
        pdfDocument.save(_dataDir + "高性能mysql第四版.html", SaveFormat.Html);
    }

    public static void ConvertWordToFDF() {
        // Open the source PDF document
        String _dataDir = "C:\\Users\\y'y\\Desktop\\资料\\go\\";
        Document pdfDocument = new Document(_dataDir + "PDF-to-DOC.doc");
        pdfDocument.save(_dataDir + "out.pdf", SaveFormat.Pdf);
    }

    public static void ConvertPDFtoWordDocAdvanced() {
        String _dataDir = "C:\\Users\\y'y\\Desktop\\资料\\go\\";
        Path pdfFile = Paths.get(_dataDir.toString(), "Go.pdf");
        Path docFile = Paths.get(_dataDir.toString(), "PDF-to-DOC.doc");
        Document pdfDocument = new Document(pdfFile.toString());
        DocSaveOptions saveOptions = new DocSaveOptions();

        // Specify the output format as DOC
        saveOptions.setFormat(DocSaveOptions.DocFormat.Doc);
        // Set the recognition mode as Flow
        saveOptions.setMode(DocSaveOptions.RecognitionMode.Flow);

        // Set the Horizontal proximity as 2.5
        saveOptions.setRelativeHorizontalProximity(2.5f);

        // Enable the value to recognize bullets during conversion process
        saveOptions.setRecognizeBullets(true);

        pdfDocument.save(docFile.toString(), saveOptions);
    }

    public static void ConvertPDFtoHTML_SplittingOutput() {
        String _dataDir = "C:\\Users\\y'y\\Desktop\\资料\\mysql\\";
        // Open the source PDF document
        Document pdfDocument = new Document(_dataDir + "OReilly.High.Performance.MySQL.4th.Edition.2021.11.pdf");

        // Instantiate HTML SaveOptions object
        HtmlSaveOptions htmlOptions = new HtmlSaveOptions();

        // Specify to split the output into multiple pages
        htmlOptions.setSplitIntoPages(true);


        // Save the document
        pdfDocument.save(_dataDir + "MultiPageHTML_out.html", htmlOptions);
    }


}
