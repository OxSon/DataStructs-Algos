package autoComplete;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

class Parser {
    private static Term[] terms = new Term[0]; //have something to return even if parse fails

    private Parser() {
    }

    static Term[] parse(String path) {
        try (BufferedReader rdr = new BufferedReader(new FileReader(path))) {
            //discard first line, we don't need it;
            rdr.readLine();
            terms = rdr.lines().map(Parser::parseLine).toArray(Term[]::new);

            return terms;
        } catch (FileNotFoundException e) {
            System.err.println("File not found please correct %path%");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return terms;
    }

    static String[][] lines(String path) {
        try (BufferedReader rdr = new BufferedReader(new FileReader(path))) {
            //discard first line
            rdr.readLine();

            return rdr.lines().map(s -> s.split("\t")).toArray(String[][]::new);
        } catch (FileNotFoundException e) {
            System.err.println("File not found");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //return something even if our parsing failed
        return new String[0][0];
    }

    private static Term parseLine(String line) {
        var fields = line.split("\t");
        return new Term(fields[1], Double.parseDouble(fields[0]));
    }
}
