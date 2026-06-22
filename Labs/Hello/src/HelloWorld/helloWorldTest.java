package HelloWorld;


import org.testng.annotations.Test;

class helloWorldTest{
    @Test
    void itHasAGreeting(){

        helloWorld helloWorld = new helloWorld();
        java.util.Objects.requireNonNull(helloWorld);
    }
}