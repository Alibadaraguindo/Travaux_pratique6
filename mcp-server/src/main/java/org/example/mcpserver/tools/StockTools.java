package org.example.mcpserver.tools;


import jdk.jfr.Description;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class StockTools {
        private List<Company> companies = List.of(
                new Company("Maroc Telecom","Telecom",3.6,10600,"Maroc"),
                new Company("OCP","Extraction minière",5.6,20000,"Maroc")
        );

        @Tool(description = "Get All Companies")
        public List<Company> getAllCompanies() {
                return companies;
        }


        @Tool(description = "Get a company by name")
        public Company getCompanyByName(String Name) {
                return companies.stream().filter(c->c.name().equals(Name)).findFirst()
                        .orElseThrow(()->new RuntimeException(String.format("Company  not found", Name)));
        }

        @Tool
        public Stock getStockByCompanyName(String name) {
                return new Stock(name, LocalDate.now(), 300+Math.random()*300);
        }
}

record Company(String name,String activity,
               @Description("The turnover in Milliard MAD")
               double turnover,
               int employeesCount,String country){}

record Stock(String companyName, LocalDate date, double stock){ }