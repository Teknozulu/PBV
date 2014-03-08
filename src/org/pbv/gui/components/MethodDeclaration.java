package org.pbv.gui.components;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.MethodNode;

import java.util.*;

public class MethodDeclaration implements Opcodes {

    private MethodNode mn;

    private static LinkedHashMap<Integer, String> modifiers = new LinkedHashMap();

    static {
        modifiers.put(ACC_PUBLIC, "public ");
        modifiers.put(ACC_PRIVATE, "private ");
        modifiers.put(ACC_PROTECTED, "protected ");
        modifiers.put(ACC_STATIC, "static ");
        modifiers.put(ACC_SYNCHRONIZED, "synchronized ");
        modifiers.put(ACC_VOLATILE, "volatile ");
        modifiers.put(ACC_ABSTRACT, "abstract ");
        modifiers.put(ACC_FINAL, "final ");
    }

    public MethodDeclaration(MethodNode mn) {
        this.mn = mn;
    }

    public String getDeclaration() {
        String dec = "";
        for (Map.Entry<Integer, String> e : modifiers.entrySet()) {
            if ((mn.access & e.getKey()) != 0) {
                dec += e.getValue();
            }
        }
        dec += Type.getReturnType(mn.desc).getClassName() + " " + mn.name + "(";
        for (Type t : Type.getArgumentTypes(mn.desc)) {
            dec += t.getClassName() + ", ";
        }
        dec += ")";
        return dec.replace(", )", ")");
    }

    public List<CodeLine> getCode() {
        List<CodeLine> code = new ArrayList();
        ListIterator<AbstractInsnNode> ainli = mn.instructions.iterator();
        int labelCount = 0;
        while (ainli.hasNext()) {
            AbstractInsnNode next = ainli.next();
            if (next.getOpcode() == -1) {
                code.add(new CodeLine(-1, "Label " + labelCount));
                labelCount++;
            } else {

            }
        }
        return code;
    }


}
