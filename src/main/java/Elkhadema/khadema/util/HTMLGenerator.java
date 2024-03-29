package Elkhadema.khadema.util;

import java.util.List;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import Elkhadema.khadema.DAO.DAOImplemantation.CompetanceDAO;
import Elkhadema.khadema.DAO.DAOImplemantation.ContactInfoDAO;
import Elkhadema.khadema.DAO.DAOImplemantation.PersonDAO;
import Elkhadema.khadema.domain.Competance;
import Elkhadema.khadema.domain.ContactInfo;
import Elkhadema.khadema.domain.Experience;
import Elkhadema.khadema.domain.Person;

public class HTMLGenerator {
    public static String firstModelCV(Person person, String path, List<Competance> comp, List<Experience> experiences) {
        Document doc = new Document("");
        Element html = doc.appendElement("hmtl");
        Element head = html.appendElement("head");
        head.appendElement("title").text("test");
        head.append("<link rel=\"stylesheet\" type=\"text/css\" href=\"your_stylesheet.css\">");
        head.append("<script src=\"https://kit.fontawesome.com/3ef3559250.js\" crossorigin=\"anonymous\"></script>");

        Element body = html.appendElement("body");
        Element section = body.appendElement("section");
        section.addClass("resume");

        Element resumeLeftElement = section.appendElement("div");
        resumeLeftElement.addClass("resume_left");
        Element photoElement = resumeLeftElement.appendElement("div");
        photoElement.addClass("r_profile_pic");
        photoElement.appendElement("img").attributes().put("src", "https://i.imgur.com/x3omKbe.png");
        Element aboutMeElement = resumeLeftElement.appendElement("div");
        aboutMeElement.addClass("r_aboutme");
        aboutMeElement.append("<h2>About me</h2>");
        aboutMeElement.appendElement("p").append(person.getAbout());
        // skills
        Element skillsElement = resumeLeftElement.appendElement("div");
        skillsElement.addClass("r_skills");
        skillsElement.append("<h2>skills</h2>");
        Element listSkills = skillsElement.appendElement("ul");
        if (comp != null) {
            for (Competance competance : comp) {
                Element element = listSkills.appendElement("li");
                element.append("<p><i class=\"fa-solid fa-code\"></i></p>");
                element.appendElement("p").append(competance.getTitre());
            }
        }

        Element resumeRightElement = section.appendElement("div");
        resumeRightElement.addClass("resume_right");
        // name and jon
        Element nameRole = resumeRightElement.appendElement("div");
        nameRole.appendElement("p").append(person.getFirstName());
        nameRole.appendElement("p").append(person.getLastName());
        nameRole.appendElement("p").addClass("role").append(person.getJob());
        // contact info
        Element contactInfo = resumeRightElement.appendElement("div");
        contactInfo.addClass("r_info");
        Element listInfo = contactInfo.appendElement("ul");
        listInfo.appendElement("li").append(person.getContactInfo().getEmail());
        listInfo.appendElement("li").append(String.valueOf(person.getContactInfo().getPhoneNumber()));
        listInfo.appendElement("li").append(person.getContactInfo().getAddress());

        // experience
        Element experiencElement = resumeRightElement.appendElement("div");
        experiencElement.addClass("r_jobs");
        experiencElement.append("<h2>Work Experience</h2>");
        Element experiencList = experiencElement.appendElement("ul");
        if (experiences != null) {
            experiences.forEach(experience -> {
                Element element = experiencList.appendElement("li");
                element.appendElement("div").addClass("r_ed_left").append(experience.getDateExperience());
                Element text = element.appendElement("div").addClass("r_ed_right");
                text.appendElement("p").append(experience.getTechnologie());
                text.appendElement("p").append(experience.getDescription());
            });
        }

        return doc.outerHtml();

    }

    public static void main(String[] args) {
        PersonDAO personDAO = new PersonDAO();
        ContactInfoDAO contactInfoDAO = new ContactInfoDAO();
        CompetanceDAO competanceDAO = new CompetanceDAO();
        Person person = personDAO.get(5).get();
        person.setContactInfo(contactInfoDAO.get(13).get());
        System.out.println(firstModelCV(person, "./aaa.html", competanceDAO.getAll(person), null));
    }
}
