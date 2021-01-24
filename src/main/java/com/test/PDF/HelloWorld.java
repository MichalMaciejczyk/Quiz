package com.test.PDF;

import com.itextpdf.kernel.pdf.DocumentProperties;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.test.entity.Questions;
import com.test.entity.QuestionsWrapper;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class HelloWorld {
    public static final String DEST = "results/chapter01/hello_world.pdf";

    public void createPdf(QuestionsWrapper questionswrapper) throws FileNotFoundException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        PdfWriter writer = new PdfWriter(DEST);
        PdfDocument pdf = new PdfDocument(writer);
        Document document = new Document(pdf);

        document.add(new Paragraph("Imie"));
        document.add(new Paragraph("Nazwisko"));
        document.add(new Paragraph("Grupa"));

        for(Questions quest : questionswrapper.getQuestions()){
            document.add(new Paragraph(""));
            document.add(new Paragraph(quest.text));
            if(quest.questionType == 1) {
                document.add(new Paragraph(quest.answerA));
                document.add(new Paragraph(quest.answerB));
                document.add(new Paragraph(quest.answerC));
                document.add(new Paragraph(quest.answerD));
            }
            else if (quest.questionType == 2){
                document.add(new Paragraph("......................................................................."));
            }
            else if (quest.questionType == 3){
                document.add(new Paragraph("..............................................................................................................................................."));
                document.add(new Paragraph("..............................................................................................................................................."));
                document.add(new Paragraph("..............................................................................................................................................."));
            }
        }


        document.close();
    }
}
