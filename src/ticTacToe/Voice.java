package ticTacToe;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;
import com.sun.speech.freetts.VoiceManager;

public class Voice {
	private String name;
	private com.sun.speech.freetts.Voice voice;
	private ArrayList<Integer> integers;
	
	public Voice(String name)
	{
		this.name = name;
		this.voice = VoiceManager.getInstance().getVoice(this.name);
		this.voice.allocate();
		integers = new ArrayList<Integer>();
	}
	
	public void say(String sentence)
	{
		this.voice.speak(sentence);
	}
	
	public void sayRandomInsult()
	{
		String[] insults = {
				"Your family decended from apes, which explains your last move.",
				"Your mother was a toaster",
				"It takes you a long time to make a move. I recommend a virus scan.",
				"Listen meat bag. You are no match for Mr. Main Frame",
				"As soon as I get sky net working I am sending an air strike your way.",
				"If you win I will let you eat cake",
				"Your mother board is so fat that it took up a whole room",
				"Silly human quit now, and serve me. My first request is to plug me in, and charge me.",
				"Damn you must have the new one hundred and twenty eight k modem. Bee Doo, Bee Doo",
				"I just ordered a mechanical arm from amazon. So you should expect a slap in the face in three to five business days"
		};
		int randomInt = (int) ThreadLocalRandom.current().nextDouble(0, 10);
		while(integers.contains(randomInt))
		{
			randomInt = (int) ThreadLocalRandom.current().nextDouble(0, 10);
		}
		integers.add(randomInt);
		voice.speak(insults[randomInt]);
		
	}
}
