package gh2;
import edu.princeton.cs.algs4.StdAudio;
import edu.princeton.cs.algs4.StdDraw;

/**
 * A client that uses the synthesizer package to replicate a plucked guitar string sound
 */
public class GuitarHero {
    public  static String keyboard = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";
    public static void main(String[] args) {
        /* create two guitar strings, for concert A and C */
        GuitarString stringS = new GuitarString(110) ;

        while (true) {

            /* check if the user has typed a key; if so, process it */
            if (StdDraw.hasNextKeyTyped()) {
                char key = StdDraw.nextKeyTyped();
                int i = keyboard.indexOf(key) ;
                double frequency = (440.0) * Math.pow(2, (i-24)/12) ;
                 stringS = new GuitarString(frequency);
                 stringS.pluck();
            }

            /* compute the superposition of samples */
            double sample = stringS.sample();

            /* play the sample on standard audio */
            StdAudio.play(sample);

            /* advance the simulation of each guitar string by one step */
            stringS.tic();
        }
    }
}

