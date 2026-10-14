import toros.*; // toros klasöründeki bütün herşeyi çekiştirdik

public class Main {
    public static void main(String[] args) {
        int kDegeri = 40; // pdfte istendiği gibi k değeri

        Lonca kartalLonca = new Lonca("Kartal Loncası", 1071, "Cuma akşamı kale kuşatması!");
        Lonca yilanLonca = new Lonca("Yılan Loncası", 1250, "Gece sessizliğiyle güçlenir."); // 2 adet lonca oluşturuldu.

        int savasciZirh = kDegeri % 31; // Savaşçı zırh değeri belirlendi.
        Savasci savasciKarakter = new Savasci("Ayşe", kartalLonca, savasciZirh);
        Buyucu buyucuKarakter = new Buyucu("Kaan", kartalLonca);
        Okcu okcuKarakter = new Okcu("Duru", yilanLonca); // 3 farklı özellikte karakter oluşturuldu

        int kilicHasar = kDegeri < 10 ? 10 : kDegeri; // Kılıç hasarı belirlendi.
        Silah kilic = new Silah("Kılıç", kilicHasar, 100);
        Silah yay = new Silah("Yay", 12, 100); // 2 adet silah oluşturuldu.

        savasciKarakter.silahKusan(kilic);
        okcuKarakter.silahKusan(yay);// karakterlerimize silahlarını verdik

        System.out.println("=== Başlangıç Durumu ==="); // Karakterlerin başlangıç durumlarını gösteriyoruz
        savasciKarakter.bilgiGoster();
        buyucuKarakter.bilgiGoster();
        okcuKarakter.bilgiGoster();

        System.out.println("\n=== Saldırılar ==="); // savaşçıyı 3 kez saldırttık
        for (int i = 1; i <= 3; i++) {
            int hasar = savasciKarakter.saldir();
            System.out.println("Saldırı " + i + ": " + hasar + " hasar, Kılıç dayanıklılığı: " + kilic.getDayaniklilik());
        }

        System.out.println("\n=== Aynı hasar etkisi ==="); // Aynı hasarı hem savaşçıya hem büyücüye verdik zırhlarının kalitesi belli olsun :)
        savasciKarakter.hasarAl(40);
        buyucuKarakter.hasarAl(40);
        System.out.println("Savaşçı can: " + savasciKarakter.getCan());
        System.out.println("Büyücü can: " + buyucuKarakter.getCan());

        System.out.println("\n=== Büyüler ==="); // büyücüye 2 adet büyü yaptırdık ve manasını bitirdik sonra 3. büyüyü yapmaya çalıştık ve yapamadı
        System.out.println("Büyü 1 sonucu: " + buyucuKarakter.buyuYap("Ateş Topu", 30));
        System.out.println("Büyü 2 sonucu: " + buyucuKarakter.buyuYap("Buz Kılıcı", 30));
        //try-catch bloğu ile programın çökmesini engelledik ve kullanıcıya hata mesajı verdik
        try {
            buyucuKarakter.buyuYap("Yıldırım", 50);
        } catch (IllegalStateException e) {
            System.out.println("Yıldırım reddedildi: " + e.getMessage());
        }


        System.out.println("\n=== Ok atışı ==="); // okçuyu 2 kez saldırttık ve kalan ok sayısını gösterdik
        System.out.println("Ok 1: " + okcuKarakter.atesEt());
        System.out.println("Ok 2: " + okcuKarakter.atesEt());
        System.out.println("Kalan ok sayısı: " + okcuKarakter.getOkSayisi());


        System.out.println("\n=== İyileştirme ve seviye atlama ==="); // büyücüye can verdik seviye atlattık ve bilgilerini gösterdik
        buyucuKarakter.iyiles(30);
        buyucuKarakter.seviyeAtla();
        buyucuKarakter.bilgiGoster();

        System.out.println("\n=== Geçersiz işlemler ==="); // Geçersiz işlemler için try-catch blokları kullanıldı
        try {
            new Karakter("Al", kartalLonca);
        } catch (IllegalArgumentException e) {
            System.out.println("Hata: " + e.getMessage());
        }

        try {
            savasciKarakter.hasarAl(-5);
        } catch (IllegalArgumentException e) {
            System.out.println("Hata: " + e.getMessage());
        }

        try {
            buyucuKarakter.iyiles(0);
        } catch (IllegalArgumentException e) {
            System.out.println("Hata: " + e.getMessage());
        }

        System.out.println("\n=== Duyuru değişimi ===");
        kartalLonca.setDuyuru("Kale kuşatması yarın sabah başlıyor!");
        savasciKarakter.bilgiGoster();
        buyucuKarakter.bilgiGoster();

        System.out.println("\n=== toString örneği ===");
        System.out.println(okcuKarakter);

        System.out.println("\n=== Suikastçı ===");
        Suikastci suikastciKarakter = new Suikastci("Selin", kartalLonca);
        suikastciKarakter.gizlen();
        System.out.println("Suikastçı ilk saldırı: " + suikastciKarakter.saldir());
        System.out.println("Suikastçı ikinci saldırı: " + suikastciKarakter.saldir());
        System.out.println(suikastciKarakter);
    }
}