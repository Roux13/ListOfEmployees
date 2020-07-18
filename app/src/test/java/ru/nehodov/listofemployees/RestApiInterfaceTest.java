package ru.nehodov.listofemployees;

import com.google.gson.Gson;

import org.junit.Test;

import ru.nehodov.listofemployees.models.Response;

public class RestApiInterfaceTest {

    private String json = "{\n" +
            "  \"response\": [\n" +
            "              {\n" +
            "                \"f_name\": \"иВан\",\n" +
            "                \"l_name\": \"ИваноВ\",\n" +
            "                \"birthday\": \"1987-03-23\",\n" +
            "                \"avatr_url\": \"http://img2.russia.ru/upimg/author/451/image.jpg\",\n" +
            "                \"specialty\": [\n" +
            "                            {\n" +
            "                              \"specialty_id\": 101,\n" +
            "                              \"name\": \"Менеджер\"\n" +
            "                            }\n" +
            "                           ]\n" +
            "              },\n" +
            "              {\n" +
            "                \"f_name\": \"Петр\",\n" +
            "                \"l_name\": \"петроВ\",\n" +
            "                \"birthday\": null,\n" +
            "                \"avatr_url\": \"http://img2.russia.ru/upimg/author/489/image.jpg\",\n" +
            "                \"specialty\": [\n" +
            "                            {\n" +
            "                              \"specialty_id\": 101,\n" +
            "                              \"name\": \"Менеджер\"\n" +
            "                            }\n" +
            "                           ]\n" +
            "              },\n" +
            "              {\n" +
            "                \"f_name\": \"Вася\",\n" +
            "                \"l_name\": \"Пупкин\",\n" +
            "                \"birthday\": \"1985-11-29\",\n" +
            "                \"avatr_url\": \"http://img2.russia.ru/upimg/author/422/image.jpg\",\n" +
            "                \"specialty\": [\n" +
            "                            {\n" +
            "                              \"specialty_id\": 101,\n" +
            "                              \"name\": \"Менеджер\"\n" +
            "                            },\n" +
            "                            {\n" +
            "                              \"specialty_id\": 102,\n" +
            "                              \"name\": \"Разработчик\"\n" +
            "                             }\n" +
            "                           ]\n" +
            "              },\n" +
            "              {\n" +
            "                \"f_name\": \"ЕКАТЕРИНА\",\n" +
            "                \"l_name\": \"пертрова\",\n" +
            "                \"birthday\": \"1990-01-07\",\n" +
            "                \"avatr_url\": \"\",\n" +
            "                \"specialty\": [\n" +
            "                            {\n" +
            "                              \"specialty_id\": 102,\n" +
            "                              \"name\": \"Разработчик\"\n" +
            "                            }\n" +
            "                           ]\n" +
            "              },\n" +
            "              {\n" +
            "                \"f_name\": \"Николай\",\n" +
            "                \"l_name\": \"Сидоров\",\n" +
            "                \"birthday\": \"\",\n" +
            "                \"avatr_url\": null,\n" +
            "                \"specialty\": [\n" +
            "                            {\n" +
            "                              \"specialty_id\": 102,\n" +
            "                              \"name\": \"Разработчик\"\n" +
            "                            }\n" +
            "                           ]\n" +
            "              },\n" +
            "              {\n" +
            "                \"f_name\": \"Виктор\",\n" +
            "                \"l_name\": \"Федотов\",\n" +
            "                \"birthday\": \"23-07-2000\",\n" +
            "                \"avatr_url\": \"http://img2.russia.ru/upimg/author/317/image.jpg\",\n" +
            "                \"specialty\": [\n" +
            "                            {\n" +
            "                              \"specialty_id\": 102,\n" +
            "                              \"name\": \"Разработчик\"\n" +
            "                            }\n" +
            "                           ]\n" +
            "              },\n" +
            "              {\n" +
            "                \"f_name\": \"Артур\",\n" +
            "                \"l_name\": \"ВАрламов\",\n" +
            "                \"birthday\": \"23-07-2000\",\n" +
            "                \"avatr_url\": \"http://img2.russia.ru/upimg/author/316/image.jpg\",\n" +
            "                \"specialty\": [\n" +
            "                            {\n" +
            "                              \"specialty_id\": 102,\n" +
            "                              \"name\": \"Разработчик\"\n" +
            "                            }\n" +
            "                           ]\n" +
            "              },\n" +
            "              {\n" +
            "                \"f_name\": \"Артур\",\n" +
            "                \"l_name\": \"ВАрламов\",\n" +
            "                \"birthday\": \"23-07-1982\",\n" +
            "                \"avatr_url\": \"http://img2.russia.ru/upimg/author/313/image.jpg\",\n" +
            "                \"specialty\": [\n" +
            "                            {\n" +
            "                              \"specialty_id\": 102,\n" +
            "                              \"name\": \"Разработчик\"\n" +
            "                            }\n" +
            "                           ]\n" +
            "              },\n" +
            "              {\n" +
            "                \"f_name\": \"Руслан\",\n" +
            "                \"l_name\": \"Русанов\",\n" +
            "                \"birthday\": \"17-10-1984\",\n" +
            "                \"avatr_url\": \"http://img2.russia.ru/upimg/author/313/mage.jpg\",\n" +
            "                \"specialty\": [\n" +
            "                            {\n" +
            "                              \"specialty_id\": 102,\n" +
            "                              \"name\": \"Разработчик\"\n" +
            "                            }\n" +
            "                           ]\n" +
            "              },\n" +
            "              {\n" +
            "                \"f_name\": \"Владимир\",\n" +
            "                \"l_name\": \"Миронов\",\n" +
            "                \"birthday\": \"03-08-1972\",\n" +
            "                \"avatr_url\": \"http://img2.russia.ru/upimg/author/233/image.jpg\",\n" +
            "                \"specialty\": [\n" +
            "                            {\n" +
            "                              \"specialty_id\": 102,\n" +
            "                              \"name\": \"Разработчик\"\n" +
            "                            }\n" +
            "                           ]\n" +
            "              }\n" +
            "            ]\n" +
            "}\n";

    @Test
    public void fromJson() {
        Gson gson = new Gson();
        Response response = gson.fromJson(json, Response.class);
        System.out.println(response);
    }

}