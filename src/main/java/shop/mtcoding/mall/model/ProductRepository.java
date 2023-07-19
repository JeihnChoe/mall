package shop.mtcoding.mall.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Repository // 컴포넌트 스캔 - IOC 컨테이너 띄움
public class ProductRepository {

    @Autowired // IOC 컨테이너에서 찾아서 주입해준다는 뜻. DI(의존성주입)
    private EntityManager em;

    @Transactional
    public void save(String name, int price, int qty){
        Query query = em.createNativeQuery("insert into product_tb(name, price, qty) values(:name, :price, :qty)");
                query.setParameter("name", name);
                query.setParameter("price", price);
                query.setParameter("qty", qty);
                query.executeUpdate();
    }
    public List<Product> findAll() {
        Query query = em.createNativeQuery("select * from product_tb", Product.class);
        List<Product> productList = query.getResultList();
        return productList;
    }
}

