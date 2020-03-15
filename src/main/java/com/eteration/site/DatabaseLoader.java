package com.eteration.site;

import com.eteration.site.Model.*;
import com.eteration.site.Services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class DatabaseLoader implements CommandLineRunner {
    private BCryptPasswordEncoder encoder ;
    private CategoryService categoryService;
    private ProductService productService;
    private RoleService roleService;
    private UserService userService;
    private CommentService commentService;
    private BasketService basketService;


    public DatabaseLoader(CategoryService categoryService, ProductService productService,
                          RoleService roleService, UserService userService, CommentService commentService,BasketService basketService) {
        this.categoryService = categoryService;
        this.productService = productService;
        this.roleService = roleService;
        this.userService = userService;
        this.basketService=basketService;
        this.commentService=commentService;
        encoder=new BCryptPasswordEncoder();
    }

    @Override
    public void run(String... args) throws Exception {

        String passwordAdmin = "{bcrypt}"+encoder.encode("admin");
        String passwordUser = "{bcrypt}"+encoder.encode("user");
        String passwordSite = "{bcrypt}"+encoder.encode("site");
        System.out.println("deneme "+encoder);
        Role adminRole = new Role("ROLE_ADMIN");
        Role userRole = new Role("ROLE_USER");
        User admin = new User("Admin isim","admin@gmail.com",passwordAdmin);
        User user = new User("user isim","user@gmail.com",passwordUser);
        User site = new User("site","site@gmail.com",passwordSite);
        admin.addRole(adminRole);
        user.addRole(userRole);
        site.addRole(userRole);
        roleService.save(adminRole);
        userService.save(admin);
        roleService.save(userRole);
        userService.save(user);

        userService.save(site);

        Category elektronik = new Category("Elektronik");
        Category bilgisayar = new Category("Bilgisayar");
        Category telefon = new Category("Telefon");
        Category kitap = new Category("Kitap");
        Category ders = new Category("Ders Kitapları");
        Category edebiyat = new Category("Edebiyat");
        Category roman = new Category("Roman");
        Category cizgiRoman = new Category("Çizgi Roman");

        elektronik.addSubCategory(bilgisayar);
        elektronik.addSubCategory(telefon);

        kitap.addSubCategory(ders);
        kitap.addSubCategory(edebiyat);

        edebiyat.addSubCategory(roman);
        edebiyat.addSubCategory(cizgiRoman);

        categoryService.save(elektronik);
        categoryService.save(kitap);

        categoryService.save(bilgisayar);
        categoryService.save(telefon);

        categoryService.save(edebiyat);
        categoryService.save(ders);
        categoryService.save(roman);
        categoryService.save(cizgiRoman);

        ////////////////////////////////////////

        Product bilgisayar1 = new Product("Bilgisayar 1 name".toLowerCase(),1111,"Bilgisayar 1 desc".toLowerCase(),"Bilgisayar 1 image",site);
        bilgisayar1.addCategory(bilgisayar);
        productService.save(bilgisayar1);

        Product bilgisayar2 = new Product("Bilgisayar 2 name".toLowerCase(),2222,"Bilgisayar 2 desc".toLowerCase(),"Bilgisayar 2 image",site);
        bilgisayar2.addCategory(bilgisayar);
        productService.save(bilgisayar2);

        Product bilgisayar3 = new Product("Bilgisayar 3 name".toLowerCase(),3333,"Bilgisayar 3 desc".toLowerCase(),"Bilgisayar 3 image",site);
        bilgisayar3.addCategory(bilgisayar);
        productService.save(bilgisayar3);

        ////////////////////////////////////////

        Product telefon1 = new Product("Telefon 1 name".toLowerCase(),1111,"Telefon 1 desc".toLowerCase(),"Telefon 1 image",site);
        telefon1.addCategory(telefon);
        productService.save(telefon1);

        Product telefon2 = new Product("Telefon 2 name".toLowerCase(),22222,"Telefon 2 desc".toLowerCase(),"Telefon 2 image",site);
        telefon2.addCategory(telefon);
        productService.save(telefon2);

        Product telefon3 = new Product("Telefon 3 name".toLowerCase(),33333,"Telefon 3 desc".toLowerCase(),"Telefon 3 image",site);
        telefon3.addCategory(telefon);
        productService.save(telefon3);

        Product telefon4 = new Product("Telefon 4 name".toLowerCase(),44444,"Telefon 4 desc".toLowerCase(),"Telefon 4 image",site);
        telefon4.addCategory(telefon);
        productService.save(telefon4);

        Product telefon5 = new Product("Telefon 5 name".toLowerCase(),5555,"Telefon 5 desc".toLowerCase(),"Telefon 5 image",site);
        telefon5.addCategory(telefon);
        productService.save(telefon5);

        ////////////////////////////////////////

        Product ders1 = new Product("ders 1 name".toLowerCase(),1111,"ders 1 desc".toLowerCase(),"ders 1 image",site);
        ders1.addCategory(ders);
        productService.save(ders1);

        Product ders2 = new Product("ders 2 name".toLowerCase(),22222,"ders 2 desc".toLowerCase(),"ders 2 image",site);
        ders2.addCategory(ders);
        productService.save(ders2);

        ////////////////////////////////////////

        Product roman1 = new Product("roman 1 name".toLowerCase(),1111,"roman 1 desc".toLowerCase(),"roman 1 image",site);
        roman1.addCategory(roman);
        productService.save(roman1);

        Product roman2 = new Product("roman 2 name".toLowerCase(),22222,"roman 2 desc".toLowerCase(),"roman 2 image",site);
        roman2.addCategory(roman);
        productService.save(roman2);

        Product roman3 = new Product("roman 3 name".toLowerCase(),33333,"roman 3 desc".toLowerCase(),"roman 3 image",site);
        roman3.addCategory(roman);
        productService.save(roman3);

        ////////////////////////////////////////

        Product cizgiRoman1 = new Product("cizgiRoman 1 name".toLowerCase(),1111,"cizgiRoman 1 desc".toLowerCase(),"cizgiRoman 1 image",site);
        cizgiRoman1.addCategory(cizgiRoman);
        productService.save(cizgiRoman1);

        Product cizgiRoman2 = new Product("cizgiRoman 2 name".toLowerCase(),22222,"cizgiRoman 2 desc".toLowerCase(),"cizgiRoman 2 image",site);
        cizgiRoman2.addCategory(cizgiRoman);
        productService.save(cizgiRoman2);

        Product cizgiRoman3 = new Product("cizgiRoman 3 name".toLowerCase(),22222,"cizgiRoman 3 desc".toLowerCase(),"cizgiRoman 3 image",site);
        cizgiRoman3.addCategory(cizgiRoman);
        productService.save(cizgiRoman3);

        ////////////////////////////////////////


        Comment c1 = new Comment("atarcalar","Ürün güzel alınabilir.",bilgisayar1);
        Comment c2 = new Comment("atarcalar","Ürün güzel alınabilir.",bilgisayar2);
        Comment c3 = new Comment("atarcalar","Ürün güzel alınabilir.",bilgisayar3);
        Comment c4 = new Comment("atarcalar","Ürün güzel alınabilir.",telefon1);
        Comment c5 = new Comment("atarcalar","Ürün güzel alınabilir.",telefon2);
        Comment c6 = new Comment("atarcalar","Ürün güzel alınabilir.",telefon3);
        Comment c7 = new Comment("atarcalar","Ürün güzel alınabilir.",telefon4);
        Comment c8 = new Comment("atarcalar","Ürün güzel alınabilir.",telefon5);
        Comment c9 = new Comment("atarcalar","Ürün güzel alınabilir.",ders1);
        Comment c10 = new Comment("atarcalar","Ürün güzel alınabilir.",ders2);
        Comment c11= new Comment("atarcalar","Ürün güzel alınabilir.",roman1);
        Comment c12 = new Comment("atarcalar","Ürün güzel alınabilir.",roman2);
        Comment c13 = new Comment("atarcalar","Ürün güzel alınabilir.",roman3);
        Comment c14 = new Comment("atarcalar","Ürün güzel alınabilir.",cizgiRoman1);
        Comment c15 = new Comment("atarcalar","Ürün güzel alınabilir.",cizgiRoman2);
        Comment c16 = new Comment("atarcalar","Ürün güzel alınabilir.",cizgiRoman3);



        commentService.save(c1);
        commentService.save(c2);
        commentService.save(c3);
        commentService.save(c4);
        commentService.save(c5);
        commentService.save(c6);
        commentService.save(c7);
        commentService.save(c8);
        commentService.save(c9);
        commentService.save(c10);
        commentService.save(c11);
        commentService.save(c12);
        commentService.save(c13);
        commentService.save(c14);
        commentService.save(c15);
        commentService.save(c16);


        Basket basket = new Basket();
        basketService.save(basket);

    }
}
