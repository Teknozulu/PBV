package org.pbv.asm;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class SkipMaxMethodVisitor extends MethodVisitor {

    public SkipMaxMethodVisitor(MethodVisitor mv) {
        super(Opcodes.ASM5, mv);
    }

    @Override
    public void visitMaxs(int maxStack, int maxLocals) {
    }
}
