package main.markdown.parser;



import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public abstract class Lexer {

    public static List<Object> parse(Iterator<String> code) {

        List<Object> tokenList = new LinkedList<>();


        while(code.hasNext()) {
            Character c = code.next();
            switch (c) {
                case ' ':
                case '\n':
                    tokenList.add(tokenMatcher(currentToken.toString()));
                    currentToken = new StringBuilder();
                    break;

                default:
                    currentToken.append(c);
                    break;
            }


        }


        return tokenList;
    }

}
