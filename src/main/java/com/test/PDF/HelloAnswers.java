package com.test.PDF;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Text;
import com.test.entity.Questions;
import com.test.entity.QuestionsWrapper;

import java.io.File;
import java.io.FileNotFoundException;

public class HelloAnswers {
    public  String DEST = "";

    public void createPdf(QuestionsWrapper questionswrapper, String path) throws FileNotFoundException {
        DEST = path;
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        PdfWriter writer = new PdfWriter(DEST);
        PdfDocument pdf = new PdfDocument(writer);
        Document document = new Document(pdf);

        document.add(new Paragraph("Poprawne Odpowiedzi"));


        for(Questions quest : questionswrapper.getQuestions()){
            if(quest.questionType == 1) {
                document.add(new Paragraph(quest.text));
                String odpowiedzi = "";
                boolean przecinek = true;
                for(Integer answer:quest.rightAnswer){
                    switch (answer){
                        case 1: {odpowiedzi += "A";
                            if(przecinek)
                                odpowiedzi  += ",";
                            przecinek = true;
                            break;
                        }
                        case 2: {odpowiedzi += "B";
                            if(przecinek)
                                odpowiedzi  += ",";
                            przecinek = true;
                            break;
                        }
                        case 3: {odpowiedzi += "C";
                            if(przecinek)
                                odpowiedzi  += ",";
                            przecinek = true;
                            break;
                        }
                        case 4: {odpowiedzi += "D";
                            if(przecinek)
                                odpowiedzi  += ",";
                            przecinek = true;
                            break;
                        }

                }


                }
                document.add(new Paragraph(odpowiedzi));
            }
    }
        document.close();
}
}
