package seominkim.puppyAlert.global.utils;

public class ImageSearcherTest {

    public void imageSearcherTest(){
        String keyword = "피자";
        System.out.println(ImageSearcher.getImageURL(keyword));

        keyword = "햄버거";
        System.out.println(ImageSearcher.getImageURL(keyword));

        keyword = "삼계탕";
        System.out.println(ImageSearcher.getImageURL(keyword));

        keyword = "제육볶음";
        System.out.println(ImageSearcher.getImageURL(keyword));

        keyword = "콩국수";
        System.out.println(ImageSearcher.getImageURL(keyword));

        keyword = "비빔밥";
        System.out.println(ImageSearcher.getImageURL(keyword));
    }
}