package org.pbv.asm;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class TargetMethodVisitor extends ClassVisitor {

    private String targetName;
    private String targetDescriptor;

    public TargetMethodVisitor(ClassVisitor classVisitor, String targetName, String targetDescriptor) {
        super(Opcodes.ASM5, classVisitor);
        this.targetName = targetName;
        this.targetDescriptor = targetDescriptor;
    }

    @Override
    public MethodVisitor visitMethod(int i, String name, String desc, String sig, String[] exceptions) {
        if (targetName.equals(name) && targetDescriptor.equals(desc)) {
            return new SkipMaxMethodVisitor(super.visitMethod(i, name, desc, sig, exceptions));
        }
        return null;
    }
}