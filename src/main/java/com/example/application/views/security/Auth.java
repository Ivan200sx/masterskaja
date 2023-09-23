package com.example.application.views.security;

import com.example.application.views.AuthLayout;
import com.example.application.views.MainLayout;
import com.example.application.views.helloworld.HelloWorldView;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.dom.Element;
import com.vaadin.flow.router.*;
import org.springframework.beans.factory.annotation.Autowired;

@Route("/")
public class Auth extends Div implements BeforeEnterObserver{
//    public class Auth extends Div
    // implements BeforeEnterObserver
    @Autowired
    Passwd passwd;

    boolean session = false;
    public Auth() {

        session = false;
        TextField key = new TextField("Key");
        Button check = new Button("Check");
        check.addClickListener(e -> {
            passwd.getHash(key.getValue());
            System.out.println("9f86d081884c7d659a2feaa0c55ad015a3bf4f1b2b0b822cd15d6c15b0f00a08" + " == " + passwd.getHash(key.getValue()));
//            (passwd.getHash(key.getValue()).equals("9f86d081884c7d659a2feaa0c55ad015a3bf4f1b2b0b822cd15d6c15b0f00a08"))? Notification.show("Hello " + key.getValue()):Notification.show("Wrong key");
            if(passwd.getHash(key.getValue()).equals("9f86d081884c7d659a2feaa0c55ad015a3bf4f1b2b0b822cd15d6c15b0f00a08")) {
                Notification.show("Shalom " + key.getValue(), 3000, Notification.Position.MIDDLE);
                check.getUI().ifPresent(ui ->
                        ui.navigate("main"));
            }else{
                Notification.show("Wrong key", 3000, Notification.Position.MIDDLE);
            }
        });
        check.addClickShortcut(Key.ENTER);
        VerticalLayout verticalLayout = new VerticalLayout();
        verticalLayout.setMargin(true);
        verticalLayout.setAlignItems(FlexComponent.Alignment.CENTER);
        verticalLayout.add(key, check);
        HorizontalLayout horizontalLayout = new HorizontalLayout();
        horizontalLayout.setMargin(true);
        horizontalLayout.setAlignItems(FlexComponent.Alignment.CENTER);
//        horizontalLayout.add(key, check);
        horizontalLayout.add(verticalLayout);
        add(horizontalLayout);

//        LoginForm loginForm = new LoginForm();
//        // Implementation details omitted
//        add(loginForm);
    }

    @Override
    public void beforeEnter(BeforeEnterEvent beforeEnterEvent) {
        if (session == true) {
            beforeEnterEvent.rerouteTo(HelloWorldView.class);
        }
    }
}




//@PageTitle("Authorization page")
//@Route(value = "auth", layout = AuthLayout.class)
//@RouteAlias(value = "", layout = AuthLayout.class)
//public class Auth extends HorizontalLayout implements RouterLayout  {
//
//    private TextField name;
//    private Button sayHello;
//
//    public Auth() {
//        name = new TextField("Get pass");
//        sayHello = new Button("Check pass");
//        sayHello.addClickListener(e -> {
//            Notification.show("Hello " + name.getValue());
//        });
//        sayHello.addClickShortcut(Key.ENTER);
//
////
//        setMargin(true);
//        setVerticalComponentAlignment(Alignment.END, name, sayHello);
//
//        add(name, sayHello);
//    }
//
//    @Override
//    public Element getElement() {
//        return null;
//    }
//}
