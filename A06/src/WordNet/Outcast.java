package WordNet;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class Outcast
{
    private final WordNet wd;

    // Constructor takes a WOrkNet object.
    public Outcast(WordNet wd)
    {
        this.wd = wd;
    }

    // Given an array of WordNet nouns, return an outcast.
    public String outcast(String[] nouns)
    {
        if(nouns == null)
            throw new java.lang.NullPointerException("Input cannot be null!");

        String outcast  = null;
        int distance    =  0;
        int maxDistance = -1;

        for(int i = 0; i < nouns.length; i++)
        {
            for(int j = 0;  j < nouns.length; j++)
            {
                if(j != i)
                    distance += wd.distance(nouns[i], nouns[j]);
            }

            if(maxDistance < 0 || distance > maxDistance)
            {
                maxDistance = distance;
                outcast     = nouns[i];
            }
            distance = 0;
        }


        return outcast;
    }

    // Unit testing. ---------------------------------------------------------------------------------------------------
    public static void main(String[] args)
    {
        WordNet wordnet = new WordNet("synsets.txt", "hypernyms.txt");
        Outcast outcast = new Outcast(wordnet);

        String[] files = {"outcast5.txt",  "outcast8.txt",  "outcast10.txt",
                          "outcast11.txt", "outcast12.txt", "outcast17.txt"};

        for (int t = 0; t < files.length; t++)
        {
            In in          = new In(files[t]);
            String[] nouns = in.readAllStrings();

            StdOut.println(files[t] + ": " + outcast.outcast(nouns));
        }
    }
}
