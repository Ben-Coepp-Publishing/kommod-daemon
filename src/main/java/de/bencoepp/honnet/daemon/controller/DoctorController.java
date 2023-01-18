package de.bencoepp.honnet.daemon.controller;

import de.bencoepp.entity.Check;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
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
        kubectlCheck.setDescription(output);
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


    public static String execCmd(String cmd) throws java.io.IOException {
        java.util.Scanner s = new java.util.Scanner(Runtime.getRuntime().exec(cmd.split(" ")).getInputStream()).useDelimiter("\\A");
        return s.hasNext() ? s.next() : "";
    }
}
