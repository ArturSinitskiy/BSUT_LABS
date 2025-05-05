import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        try {
            // 1. Валидация XML по XSD
            validateXML("library.xml", "library.xsd");

            // 2. Парсинг XML
            List<Book> books = parseXML("library.xml");

            // 3. Вывод всех книг
            System.out.println("Список всех книг:");
            books.forEach(System.out::println);

            // 4. Расчет средней цены
            double avgPrice = books.stream()
                    .mapToDouble(Book::getPrice)
                    .average()
                    .orElse(0);
            System.out.printf("\nСредняя цена книг: %.2f\n", avgPrice);

            // 5. Фильтрация по жанру
            System.out.println("\nКниги жанра 'Роман':");
            books.stream()
                    .filter(b -> b.getGenre().equals("Роман"))
                    .forEach(System.out::println);

        } catch (Exception e) {
            System.err.println("Ошибка: " + e.getMessage());
        }
    }

    // Валидация XML по XSD
    private static void validateXML(String xmlFile, String xsdFile) throws Exception {
        SchemaFactory factory = SchemaFactory.newInstance("http://www.w3.org/2001/XMLSchema");
        Schema schema = factory.newSchema(new File(xsdFile));
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setSchema(schema);
        DocumentBuilder builder = dbf.newDocumentBuilder();
        builder.parse(new File(xmlFile)); // Выбросит исключение, если XML невалиден
        System.out.println("XML валиден по XSD схеме.");
    }

    // Парсинг XML в список объектов Book
    private static List<Book> parseXML(String xmlFile) throws Exception {
        List<Book> books = new ArrayList<>();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(new File(xmlFile));

        NodeList bookNodes = doc.getElementsByTagName("book");
        for (int i = 0; i < bookNodes.getLength(); i++) {
            Element bookElement = (Element) bookNodes.item(i);
            Book book = new Book(
                    bookElement.getAttribute("id"),
                    bookElement.getElementsByTagName("title").item(0).getTextContent(),
                    bookElement.getElementsByTagName("author").item(0).getTextContent(),
                    Integer.parseInt(bookElement.getElementsByTagName("year").item(0).getTextContent()),
                    bookElement.getElementsByTagName("genre").item(0).getTextContent(),
                    new BigDecimal(bookElement.getElementsByTagName("price").item(0).getTextContent())
            );
            books.add(book);
        }
        return books;
    }

    // Класс для хранения данных о книге
    static class Book {
        private String id;
        private String title;
        private String author;
        private int year;
        private String genre;
        private BigDecimal price;

        public Book(String id, String title, String author, int year, String genre, BigDecimal price) {
            this.id = id;
            this.title = title;
            this.author = author;
            this.year = year;
            this.genre = genre;
            this.price = price;
        }

        public double getPrice() {
            return price.doubleValue();
        }

        public String getGenre() {
            return genre;
        }

        @Override
        public String toString() {
            return String.format(
                    "ID: %s\nНазвание: %s\nАвтор: %s\nГод: %d\nЖанр: %s\nЦена: %.2f\n",
                    id, title, author, year, genre, price
            );
        }
    }
}