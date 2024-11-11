package store.util.reader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import store.model.promotion.Promotion;

public class PromotionFileReader {

    private String filePath = "src/main/resources/promotions.md";
    public List<Promotion> getPromotions() {
        List<Promotion> promotions = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            br.readLine();

            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");

                String name = parts[0];
                int buy = Integer.parseInt(parts[1]);
                int get = Integer.parseInt(parts[2]);
                LocalDate startDate = LocalDate.parse(parts[3]);
                LocalDate endDate = LocalDate.parse(parts[4]);

                promotions.add(new Promotion(name, buy, get, startDate, endDate));
            }
        } catch (IOException e) {
            System.out.println("파일을 읽는 중 오류가 발생했습니다: " + e.getMessage());
        }

        return promotions;
    }
}

