package sample.simple;

import java.net.URI;
import java.util.Timer;
import java.util.TimerTask;

import org.boon.etcd.ClientBuilder;
import org.boon.etcd.Etcd;
import org.boon.etcd.Response;

import static org.boon.Boon.puts;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class HelloController {

	 Etcd client = ClientBuilder.builder().hosts(
             URI.create("http://localhost:4001")
     ).createClient();
	 
    @RequestMapping("/")
    public String index() {

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
			
			@Override
			public void run() {
				Response response = client.setTemp("foo", "New message via java client",20);
		        puts(response);				
			}
		}, 0, 15000);
        
        return "Hello ETCD!";
    }

}