package abstractfactory;

interface Button {
    void render();
}

interface TextField {
    void render();
}

interface Checkbox {
    void render();
}


class LightButton implements Button {
    public void render() {
        System.out.println("Renderizando Botão com fundo branco");
    }
}

class LightTextField implements TextField {
    public void render() {
        System.out.println("Renderizando Caixa de Texto com borda cinza clara");
    }
}

class LightCheckbox implements Checkbox {
    public void render() {
        System.out.println("Renderizando Checkbox com tema claro");
    }
}

class DarkButton implements Button {
    public void render() {
        System.out.println("Renderizando Botão com fundo cinza escuro");
    }
}

class DarkTextField implements TextField {
    public void render() {
        System.out.println("Renderizando Caixa de Texto com borda cinza escura");
    }
}

class DarkCheckbox implements Checkbox {
    public void render() {
        System.out.println("Renderizando Checkbox com tema escuro");
    }
}


interface UIComponentFactory {
    Button createButton();
    TextField createTextField();
    Checkbox createCheckbox();
}

class LightThemeFactory implements UIComponentFactory {
    public Button createButton() {
        return new LightButton();
    }

    public TextField createTextField() {
        return new LightTextField();
    }

    public Checkbox createCheckbox() {
        return new LightCheckbox();
    }
}

class DarkThemeFactory implements UIComponentFactory {
    public Button createButton() {
        return new DarkButton();
    }

    public TextField createTextField() {
        return new DarkTextField();
    }

    public Checkbox createCheckbox() {
        return new DarkCheckbox();
    }
}


class Application {
    private UIComponentFactory factory;

    public Application(UIComponentFactory factory) {
        this.factory = factory;
    }


    public void createUI() {
        Button button = factory.createButton();
        TextField textField = factory.createTextField();
        Checkbox checkbox = factory.createCheckbox();

        button.render();
        textField.render();
        checkbox.render();
    }
}

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Tema Claro ===");
        Application appClaro = new Application(new LightThemeFactory());
        appClaro.createUI();

        System.out.println();

        System.out.println("=== Tema Escuro ===");
        Application appEscuro = new Application(new DarkThemeFactory());
        appEscuro.createUI();
    }
}