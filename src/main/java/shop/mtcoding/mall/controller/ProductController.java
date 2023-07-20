package shop.mtcoding.mall.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import shop.mtcoding.mall.model.Product;
import shop.mtcoding.mall.model.ProductRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @PostMapping("/product/delete")
    public String delete(int id,HttpServletResponse response) throws IOException {
        System.out.println("delete");
        productRepository.deleteById(id);
        return "redirect:/";
    }
    @PostMapping("/product/update")
    public String delete(int id){
        System.out.println("update");
        productRepository.updateById(id);
        return "redirect:/";
    }


    @GetMapping("/product/{id}")
    public String detail(@PathVariable int id, HttpServletRequest request){
        System.out.println("id : "+id);
        Product product = productRepository.findById2(id);
        request.setAttribute("product", product);
        System.out.println(product.getId());
        System.out.println(product.getName());
        System.out.println(product.getPrice());
        System.out.println(product.getQty());
        return "detail";
    }

    @GetMapping("/")
    public String home(HttpServletRequest request) {
        List<Product> productList = productRepository.findAll();
        request.setAttribute("productList", productList);
        return "home";
    }
    //Question . home.jsp에서 비고 양식이 추가되는 경우? 뷰 자체가 바뀌어야할텐데
    @GetMapping("/write")
    public String writepage() {
        return "write";
    }
    @PostMapping("/product")
    public String write(String name, int price, int qty, HttpServletResponse response) throws IOException {
        System.out.println("name : "+name);
        System.out.println("price : "+price);
        System.out.println("qty : "+qty);

        productRepository.save(name, price, qty);
// 컨트롤러의 메서드를 재호출하는 방법 2가지
//        response.sendRedirect("/");
//        return "redirect:/";
        return "redirect:/";
    }
}