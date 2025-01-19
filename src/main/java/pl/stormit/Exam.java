package pl.stormit;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Exam {
    File examJson;
    public Exam(String fileName){
        String file = Objects.requireNonNull(this.getClass().getClassLoader().getResource(fileName))
                .getFile();
        examJson = new File(file);
    }

    public void displayQuestion() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        List<Question> questions = mapper.readValue(examJson, new TypeReference<>() {});

        questions.stream()
                .map(Question::getQuestion)
                .forEach(System.out::println);

    }

    public void play() throws IOException {
        Scanner scanner = new Scanner(System.in);
        int points=0;
        ObjectMapper mapper = new ObjectMapper();
        List<Question> questions = mapper.readValue(examJson, new TypeReference<>() {});

        for (Question question : questions) {
            System.out.println(question.getQuestion());
            System.out.println("a: " + question.getA());
            System.out.println("b: " + question.getB());
            System.out.println("c: " + question.getC());
            System.out.println("Witaj serdecznie! Podaj prawidłową odpowiedź");

            String answer = scanner.nextLine();

            if (question.getCorrectAnswer().equals(answer)) {
                System.out.println("Brawo! To jest prawidłowa odpowiedź!");
                points++;
            }else {
                System.out.println("Twoja odpowiedź jest błędna. Prawidłowa odpowiedź to " + question.getCorrectAnswer());
            }

        }
        System.out.println("Koniec testu. Twoja liczba punktów to "   +   points);

        scanner.close();

    }

}
