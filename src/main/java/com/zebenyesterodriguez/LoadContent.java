package com.zebenyesterodriguez;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Configuration;

import com.zebenyesterodriguez.model.PriceReduction;
import com.zebenyesterodriguez.model.Product;
import com.zebenyesterodriguez.model.ProductDeactivation;
import com.zebenyesterodriguez.model.Role;
import com.zebenyesterodriguez.model.Status;
import com.zebenyesterodriguez.model.Supplier;
import com.zebenyesterodriguez.model.User;
import com.zebenyesterodriguez.repository.PriceReductionRepository;
import com.zebenyesterodriguez.repository.RoleRepository;
import com.zebenyesterodriguez.repository.StatusRepository;
import com.zebenyesterodriguez.repository.SupplierRepository;
import com.zebenyesterodriguez.service.ProductDeactivationService;
import com.zebenyesterodriguez.service.ProductService;
import com.zebenyesterodriguez.service.UserService;

@Configuration
public class LoadContent implements ApplicationRunner{

	//TODO: crear servicees y sus implementaciones. Cambiar repositorios por los services.
	@Autowired
	private RoleRepository repoRole;
	
	@Autowired
	private SupplierRepository repoSupplier;
	
	@Autowired
	private StatusRepository repoStatus;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ProductService productService;

	@Autowired
	private ProductDeactivationService productDeactivationService;
	
	@Autowired
	private PriceReductionRepository repoPriceReduction;	
	
    @Override
    public void run(ApplicationArguments args) throws Exception {
    	
    	// Cargar el contenido estático para las pruebas
//    	List<Supplier> suppliers = null;
//    	List<PriceReduction> priceReductions = null;
//    	
//		// Roles
//		Role r1 = new Role("Admin");
//		Role r2 = new Role("Employee");
//		Role r3 = new Role("Client");
//		
//		repoRole.save(r1);
//		repoRole.save(r2);
//		repoRole.save(r3);
//		
//		// Proveedores
//		Supplier s1 = new Supplier("Proveedor 1", "España");
//		Supplier s2 = new Supplier("Proveedor 2", "España");
//		Supplier s3 = new Supplier("Proveedor 3", "España");
//		Supplier s4 = new Supplier("Proveedor 4", "España");
//		Supplier s5 = new Supplier("Proveedor 5", "Francia");
//		Supplier s6 = new Supplier("Proveedor 6", "Francia");
//		Supplier s7 = new Supplier("Proveedor 7", "Alemania");
//		
//		repoSupplier.save(s1);
//		repoSupplier.save(s2);
//		repoSupplier.save(s3);
//		repoSupplier.save(s4);
//		repoSupplier.save(s5);
//		repoSupplier.save(s6);
//		repoSupplier.save(s7);
//		
//		// Estados del usuario en una tabla para poder añadir más e incluso traducciones
//		Status st1 = new Status("Active");
//		Status st2 = new Status("Discontinued");
//		
//		repoStatus.save(st1);
//		repoStatus.save(st2);
//		
//		// Usuarios
//		User u1 = new User(r1, "Bitbox", "bitbox", "info@bitbox.com", "1234", new Date());
//		User u2 = new User(r2, "Leonardo", "leo", "leo.c@bitbox.com", "1234", new Date());
//		User u3 = new User(r2, "Óliver", "oli", "oli.d@bitbox.com", "1234", new Date());
//		User u4 = new User(r2, "Adrián", "adri", "adri.m@bitbox.com", "1234", new Date());
//		
//		userService.save(u1);
//		userService.save(u2);
//		userService.save(u3);
//		userService.save(u4);
//		
//		// Reducciones de precio
//		Date date = new Date();
//	    Calendar c = Calendar.getInstance();
//	    
//	    c.setTime(date);
//	    c.add(Calendar.WEEK_OF_MONTH, 2);
//	    date = c.getTime();
//		
//		PriceReduction pR1 = new PriceReduction(5, new Date(), date);
//				
//		c.add(Calendar.WEEK_OF_MONTH, -2);
//	    date = c.getTime();
//		PriceReduction pR2 = new PriceReduction(10, new Date(), date);
//		
//		c.add(Calendar.WEEK_OF_MONTH, +4);
//	    date = c.getTime();
//		PriceReduction pR3 = new PriceReduction(15, new Date(), date);
//		
//		c.add(Calendar.WEEK_OF_MONTH, +5);
//	    date = c.getTime();
//		PriceReduction pR4 = new PriceReduction(20, new Date(), date);		
//		
//		repoPriceReduction.save(pR1);
//		repoPriceReduction.save(pR2);
//		repoPriceReduction.save(pR3);
//		repoPriceReduction.save(pR4);
//		
//		// Productos
//		Product p1 = new Product(st1, u1, "ÄPPLARÖ", 270, new Date());
//		suppliers = new ArrayList<>();
//		suppliers.add(s1);
//		suppliers.add(s2);
//		suppliers.add(s4);
//		suppliers.add(s7);
//		p1.setSuppliers(suppliers);
//		priceReductions = new ArrayList<PriceReduction>();
//		priceReductions.add(pR1);
//		priceReductions.add(pR2);
//		p1.setPriceReductions(priceReductions);
//		Product p2 = new Product(st1, u1, "KLASEN Barbacoa", 340, new Date());
//		suppliers = new ArrayList<>();
//		suppliers.add(s2);
//		suppliers.add(s4);
//		p2.setSuppliers(suppliers);
//		priceReductions = new ArrayList<PriceReduction>();
//		priceReductions.add(pR4);
//		p2.setPriceReductions(priceReductions);
//		Product p3 = new Product(st1, u1, "GRILLTIDER", 7, new Date());
//		suppliers = new ArrayList<>();
//		suppliers.add(s1);
//		suppliers.add(s6);
//		p3.setSuppliers(suppliers);
//		Product p4 = new Product(st1, u1, "TOSTERÖ Funda barbacoa", 25, new Date());
//		Product p5 = new Product(st2, u2, "KORPÖN Barbacoa carbón portátil", 20, new Date());
//		Product p6 = new Product(st2, u2, "ASKHOLMEN Enrejado", 20, new Date());		
//		Product p7 = new Product(st2, u2, "DYNING Pantalla privacidad para balcón", 17, new Date());
//		suppliers = new ArrayList<>();
//		suppliers.add(s1);
//		suppliers.add(s5);
//		suppliers.add(s7);
//		p3.setSuppliers(suppliers);
//		priceReductions = new ArrayList<PriceReduction>();
//		priceReductions.add(pR1);
//		priceReductions.add(pR2);
//		priceReductions.add(pR3);
//		priceReductions.add(pR4);
//		p3.setPriceReductions(priceReductions);
//		Product p8 = new Product(st1, u3, "BROMMÖ Tumbona jardín", 49, new Date());		
//		Product p9 = new Product(st2, u3, "SKARPÖ Sillón jardín", 50, new Date());		
//		Product p10 = new Product(st1, u4, "MASTHOLMEN Sillón jardín", 99, new Date());
//				
//		productService.save(p1);
//		productService.save(p2);
//		productService.save(p3);
//		productService.save(p4);
//		productService.save(p5);
//		productService.save(p6);
//		productService.save(p7);
//		productService.save(p8);
//		productService.save(p9);
//		productService.save(p10);
//		
//		// Desactivacion de productos
//		ProductDeactivation pD1 = new ProductDeactivation(p1, u1, new Date(), "Falta stock");
//		ProductDeactivation pD2 = new ProductDeactivation(p1, u1, new Date(), "Falta stock");
//		ProductDeactivation pD3 = new ProductDeactivation(p2, u2, new Date(), "Falta stock");
//		ProductDeactivation pD4 = new ProductDeactivation(p2, u2, new Date(), "Falta stock");
//		ProductDeactivation pD5 = new ProductDeactivation(p3, u3, new Date(), "Falta stock");
//		ProductDeactivation pD6 = new ProductDeactivation(p4, u4, new Date(), "Falta stock");
//		ProductDeactivation pD7 = new ProductDeactivation(p5, u3, new Date(), "Falta stock");
//		ProductDeactivation pD8 = new ProductDeactivation(p7, u2, new Date(), "Falta stock");
//		ProductDeactivation pD9 = new ProductDeactivation(p7, u4, new Date(), "Falta stock");
//		ProductDeactivation pD10 = new ProductDeactivation(p7, u2, new Date(), "Falta stock");
//		
//		productDeactivationService.save(pD1);
//		productDeactivationService.save(pD2);
//		productDeactivationService.save(pD3);
//		productDeactivationService.save(pD4);
//		productDeactivationService.save(pD5);
//		productDeactivationService.save(pD6);
//		productDeactivationService.save(pD7);
//		productDeactivationService.save(pD8);
//		productDeactivationService.save(pD9);
//		productDeactivationService.save(pD10);
    }

}