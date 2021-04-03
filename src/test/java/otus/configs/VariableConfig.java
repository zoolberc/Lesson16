package otus.configs;


import org.aeonbits.owner.Config;

@Config.Sources("classpath:config.xml")
public interface VariableConfig extends Config {

    @Key("emailOtus")
    String emailOtus();

    @Key("passwordOtus")
    String passwordOtus();

    @Key("VK")
    String vkContact();

    @Key("Skype")
    String skypeContact();

}
