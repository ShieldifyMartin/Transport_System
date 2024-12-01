package org.example;

import org.example.dao.CompanyDAO;
import org.example.entity.Company;
import org.example.utils.DataSeeder;

public class App {
    public static void main(String[] args) {
        DataSeeder.seed(); // seed data if necessary
        
        Company company1 = CompanyDAO.getCompanyById(1);

        // update company with id 1
        // Company company1 = CompanyDAO.getCompanyById(1);
        // company1.setName("The Emirates Group");
        // CompanyDAO.updateCompany(company1);

        // System.out.println("staff Result:");
        // CompanyDAO.getCompanyStaff(1)
        //     .stream()
        //     .forEach(System.out::println);

        // System.out.println("staffDTO Result:");
        //     CompanyDAO.getCompanyStaffDTO(1)
        //     .stream()
        //     .forEach(System.out::println);

        // CompanyDAO.getCompanies()
        //     .stream()
        //     .forEach(System.out::println);
    }
}
