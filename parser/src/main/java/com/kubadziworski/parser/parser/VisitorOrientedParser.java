package com.kubadziworski.parser.parser;

import com.kubadziworski.antlr4.visitor.SomeLanguageBaseVisitor;
import com.kubadziworski.antlr4.visitor.SomeLanguageLexer;
import com.kubadziworski.antlr4.visitor.SomeLanguageParser;
import com.kubadziworski.parser.domain.Class;
import com.kubadziworski.parser.domain.Instruction;
import com.kubadziworski.parser.domain.Method;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.misc.NotNull;

import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * Created by jdziworski on 30.03.16.
 */
public class VisitorOrientedParser implements Parser {

    public Class parse(String someLangSourceCode) {
        CharStream charStream = new ANTLRInputStream(someLangSourceCode);
        SomeLanguageLexer lexer = new SomeLanguageLexer(charStream);
        TokenStream tokens = new CommonTokenStream(lexer);
        SomeLanguageParser parser = new SomeLanguageParser(tokens);

        ClassVisitor classVisitor = new ClassVisitor();
        Class traverseResult = classVisitor.visit(parser.classDeclaration());
        return traverseResult;
    }

    private static class ClassVisitor extends SomeLanguageBaseVisitor<Class> {
        @Override
        public Class visitClassDeclaration(@NotNull SomeLanguageParser.ClassDeclarationContext ctx) {
            String className = ctx.className().getText();
            MethodVisitor methodVisitor = new MethodVisitor();
            List<Method> methods = ctx.method()
                    .stream()
                    .map(method -> method.accept(methodVisitor))
                    .collect(toList());
            return new Class(className, methods);
        }
    }

    private static class MethodVisitor extends SomeLanguageBaseVisitor<Method> {
        @Override
        public Method visitMethod(@NotNull SomeLanguageParser.MethodContext ctx) {
            String methodName = ctx.methodName().getText();
            InstructionVisitor instructionVisitor = new InstructionVisitor();
            List<Instruction> instructions = ctx.instruction()
                    .stream()
                    .map(instruction -> instruction.accept(instructionVisitor))
                    .collect(toList());
            return new Method(methodName, instructions);
        }
    }

    private static class InstructionVisitor extends  SomeLanguageBaseVisitor<Instruction> {

        @Override
        public Instruction visitInstruction(@NotNull SomeLanguageParser.InstructionContext ctx) {
            String instructionName = ctx.getText();
            return new Instruction(instructionName);
        }
    }
}
