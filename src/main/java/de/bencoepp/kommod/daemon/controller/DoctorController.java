package de.bencoepp.kommod.daemon.controller;

import de.bencoepp.entity.Check;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/api/doctor")
public class DoctorController {

    @GetMapping("/all")
    public ResponseEntity<ArrayList<Check>> getAllChecks() throws IOException {
        ArrayList<Check> checks = new ArrayList<>();
        checks.add(checkDockerVersion());
        checks.addAll(checkDockerComposeVersion());
        checks.add(checkKubectlVersion());
        checks.add(checkInternetConnection());
        return ResponseEntity.ok(checks);
    }

    private Check checkDockerVersion() throws IOException{
        Check dockerCheck = new Check();
        dockerCheck.setCommand("docker --version");
        dockerCheck.setTitle("Docker");
        dockerCheck.setDescription(execCmd("docker --version"));
        String output = execCmd("docker --version").replaceAll("\n","");
        if(output.contains("Docker version")){
            dockerCheck.setOk(true);
        }else{
            dockerCheck.setOk(false);
        }
        return dockerCheck;
    }

    private Check checkKubectlVersion() throws IOException {
        Check kubectlCheck = new Check();
        kubectlCheck.setTitle("Kubectl");
        kubectlCheck.setCommand("kubectl version");
        String output = execCmd("kubectl version");
        kubectlCheck.setDescription(output.replaceAll("\n",""));
        if(output.contains("Client Version:")){
            kubectlCheck.setOk(true);
        }else{
            kubectlCheck.setOk(false);
        }
        return kubectlCheck;
    }

    private List<Check> checkDockerComposeVersion() throws IOException {
        List<Check> dockerComposeChecks = new ArrayList<>();
        Check dockerMinusComposeCheck = new Check();
        dockerMinusComposeCheck.setTitle("Docker Compose");
        dockerMinusComposeCheck.setCommand("docker-compose --version");
        String output = execCmd("docker-compose --version").replaceAll("\n","");
        dockerMinusComposeCheck.setDescription(output);
        if(output.contains("Docker Compose version")){
            dockerMinusComposeCheck.setOk(true);
        }else{
            dockerMinusComposeCheck.setOk(false);
        }
        dockerComposeChecks.add(dockerMinusComposeCheck);
        Check dockerComposeCheck = new Check();
        dockerComposeCheck.setTitle("Docker Compose");
        dockerComposeCheck.setCommand("docker compose version");
        String out = execCmd("docker compose version").replaceAll("\n","");
        dockerComposeCheck.setDescription(out);
        if(out.contains("Docker Compose version")){
            dockerComposeCheck.setOk(true);
        }else{
            dockerComposeCheck.setOk(false);
        }
        dockerComposeChecks.add(dockerComposeCheck);
        return dockerComposeChecks;
    }

    private Check checkInternetConnection(){
        Check checkInternet = new Check();
        checkInternet.setTitle("Internet Access");
        checkInternet.setDescription("Checking if the internet is available to use");
        checkInternet.setCommand("curl https://www.google.com");
        checkInternet.setOk(netIsAvailable());
        return checkInternet;
    }

    private boolean netIsAvailable() {
        try {
            final URL url = new URL("https://www.google.com");
            final URLConnection conn = url.openConnection();
            conn.connect();
            conn.getInputStream().close();
            return true;
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            return false;
        }
    }


    public static String execCmd(String cmd) throws java.io.IOException {
        try{
            java.util.Scanner s = new java.util.Scanner(Runtime.getRuntime().exec(cmd.split(" ")).getInputStream()).useDelimiter("\\A");
            return s.hasNext() ? s.next() : "";
        }catch (Exception e){
            System.out.println(e);
            return e.toString();
        }
    }
}
