package com.kubadziworski.parser.parser;

import com.kubadziworski.antlr4.listener.SomeLanguageBaseListener;
import com.kubadziworski.antlr4.listener.SomeLanguageLexer;
import com.kubadziworski.antlr4.listener.SomeLanguageParser;
import com.kubadziworski.parser.domain.Class;
import com.kubadziworski.parser.domain.Instruction;
import com.kubadziworski.parser.domain.Method;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.misc.NotNull;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by jdziworski on 30.03.16.
 */
public class ListenerOrientedParser implements Parser{

    @Override
    public Class parse(String code) {
        CharStream charStream = new ANTLRInputStream(code);
        SomeLanguageLexer lexer = new SomeLanguageLexer(charStream);
        TokenStream tokens = new CommonTokenStream(lexer);
        SomeLanguageParser parser = new SomeLanguageParser(tokens);

        ClassListener classListener = new ClassListener();
        parser.classDeclaration().enterRule(classListener);
        return classListener.getParsedClass();
    }

    class ClassListener extends SomeLanguageBaseListener {

        private Class parsedClass;

        @Override
        public void enterClassDeclaration(@NotNull SomeLanguageParser.ClassDeclarationContext ctx) {
            String className = ctx.className().getText();
            MethodListener methodListener = new MethodListener();
            ctx.method().forEach(method -> method.enterRule(methodListener));
            Collection<Method> methods = methodListener.getMethods();
            parsedClass = new Class(className,methods);
        }

        public Class getParsedClass() {
            return parsedClass;
        }
    }

    class MethodListener extends SomeLanguageBaseListener {

        private Collection<Method> methods;

        public MethodListener() {
            methods = new ArrayList<>();
        }

        @Override
        public void enterMethod(@NotNull SomeLanguageParser.MethodContext ctx) {
            String methodName = ctx.methodName().getText();
            InstructionListener instructionListener = new InstructionListener();
            ctx.instruction().forEach(instruction -> instruction.enterRule(instructionListener));
            Collection<Instruction> instructions = instructionListener.getInstructions();
            methods.add(new Method(methodName, instructions));
        }

        public Collection<Method> getMethods() {
            return methods;
        }
    }

    class InstructionListener extends SomeLanguageBaseListener {

        private Collection<Instruction> instructions;

        public InstructionListener() {
            instructions = new ArrayList<>();
        }

        @Override
        public void enterInstruction(@NotNull SomeLanguageParser.InstructionContext ctx) {
            String instructionName = ctx.getText();
            instructions.add(new Instruction(instructionName));
        }

        public Collection<Instruction> getInstructions() {
            return instructions;
        }
    }
}
