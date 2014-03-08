package org.pbv;

import org.objectweb.asm.*;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.MethodNode;
import org.objectweb.asm.util.Printer;
import org.objectweb.asm.util.Textifier;
import org.objectweb.asm.util.TraceClassVisitor;
import org.objectweb.asm.util.TraceMethodVisitor;
import org.pbv.asm.TargetMethodVisitor;
import org.pbv.gui.MainWindow;
import org.pbv.gui.components.MethodDeclaration;

import javax.swing.*;
import java.io.*;
import java.lang.reflect.Modifier;

public class PBV {

    public static void main(String[] args) {
        System.out.println(new MethodDeclaration(new MethodNode(Modifier.PUBLIC, "calculate", "([IIIB)[Ljava/lang/String;", null, null)).getDeclaration());
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            ClassReader cr = new ClassReader("java/lang/String");
            ClassNode cn = new ClassNode();
            cr.accept(cn, 0);
            MethodNode mn = (MethodNode)cn.methods.get(1);  System.out.println(mn.instructions.size());

            PrintWriter printWriter = new PrintWriter(baos);
            TraceClassVisitor traceClassVisitor = new TraceClassVisitor(null, new SourceCodeTextifier(), printWriter);
            TargetMethodVisitor targetMethodVisitor = new TargetMethodVisitor(traceClassVisitor, mn.name, mn.desc);
            cr.accept(targetMethodVisitor, ClassReader.SKIP_DEBUG | ClassReader.SKIP_FRAMES);

            BufferedReader br = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(baos.toByteArray())));

            String line = "";
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                //new MainWindow().setVisible(true);
            }
        });
    }

    private static class SourceCodeTextifier extends Printer {

        public SourceCodeTextifier() {
            this(Opcodes.ASM4);
        }

        protected SourceCodeTextifier(final int api) {
            super(api);
        }

        @Override
        public void visit(
                final int version,
                final int access,
                final String name,
                final String signature,
                final String superName,
                final String[] interfaces)
        {
        }

        @Override
        public void visitSource(final String file, final String debug) {
        }

        @Override
        public void visitOuterClass(
                final String owner,
                final String name,
                final String desc)
        {
        }

        @Override
        public Textifier visitClassAnnotation(
                final String desc,
                final boolean visible)
        {
            return new Textifier();
        }

        @Override
        public void visitClassAttribute(final Attribute attr) {
        }

        @Override
        public void visitInnerClass(
                final String name,
                final String outerName,
                final String innerName,
                final int access)
        {
        }

        @Override
        public Textifier visitField(
                final int access,
                final String name,
                final String desc,
                final String signature,
                final Object value)
        {
            return new Textifier();
        }

        @Override
        public Textifier visitMethod(
                final int access,
                final String name,
                final String desc,
                final String signature,
                final String[] exceptions)
        {
            Textifier t = new Textifier();
            text.add(t.getText());
            return t;
        }

        @Override
        public void visitClassEnd() {
        }

        @Override
        public void visit(final String name, final Object value) {
        }


        @Override
        public void visitEnum(
                final String name,
                final String desc,
                final String value)
        {
        }

        @Override
        public Textifier visitAnnotation(
                final String name,
                final String desc)
        {
            return new Textifier();
        }

        @Override
        public Textifier visitArray(
                final String name)
        {
            return new Textifier();
        }

        @Override
        public void visitAnnotationEnd() {
        }

        @Override
        public Textifier visitFieldAnnotation(
                final String desc,
                final boolean visible)
        {
            return new Textifier();
        }

        @Override
        public void visitFieldAttribute(final Attribute attr) {
            visitAttribute(attr);
        }

        @Override
        public void visitFieldEnd() {
        }

        @Override
        public Textifier visitAnnotationDefault() {
            return new Textifier();
        }

        @Override
        public Textifier visitMethodAnnotation(
                final String desc,
                final boolean visible)
        {
            return new Textifier();
        }

        @Override
        public Textifier visitParameterAnnotation(
                final int parameter,
                final String desc,
                final boolean visible)
        {
            return new Textifier();
        }

        @Override
        public void visitMethodAttribute(final Attribute attr) {
        }

        @Override
        public void visitCode() {
        }

        @Override
        public void visitFrame(
                final int type,
                final int nLocal,
                final Object[] local,
                final int nStack,
                final Object[] stack)
        {
        }

        @Override
        public void visitInsn(final int opcode) {
        }

        @Override
        public void visitIntInsn(final int opcode, final int operand) {
        }

        @Override
        public void visitVarInsn(final int opcode, final int var) {
        }

        @Override
        public void visitTypeInsn(final int opcode, final String type) {
        }

        @Override
        public void visitFieldInsn(
                final int opcode,
                final String owner,
                final String name,
                final String desc)
        {
        }

        @Override
        public void visitMethodInsn(
                final int opcode,
                final String owner,
                final String name,
                final String desc)
        {
        }

        @Override
        public void visitInvokeDynamicInsn(
                String name,
                String desc,
                Handle bsm,
                Object... bsmArgs)
        {
        }

        @Override
        public void visitJumpInsn(final int opcode, final Label label) {
        }

        @Override
        public void visitLabel(final Label label) {
        }

        @Override
        public void visitLdcInsn(final Object cst) {
        }

        @Override
        public void visitIincInsn(final int var, final int increment) {
        }

        @Override
        public void visitTableSwitchInsn(
                final int min,
                final int max,
                final Label dflt,
                final Label... labels)
        {
        }

        @Override
        public void visitLookupSwitchInsn(
                final Label dflt,
                final int[] keys,
                final Label[] labels)
        {
        }

        @Override
        public void visitMultiANewArrayInsn(final String desc, final int dims) {
        }

        @Override
        public void visitTryCatchBlock(
                final Label start,
                final Label end,
                final Label handler,
                final String type)
        {
        }

        @Override
        public void visitLocalVariable(
                final String name,
                final String desc,
                final String signature,
                final Label start,
                final Label end,
                final int index)
        {
        }

        @Override
        public void visitLineNumber(final int line, final Label start) {
        }

        @Override
        public void visitMaxs(final int maxStack, final int maxLocals) {
        }

        @Override
        public void visitMethodEnd() {
        }

        public void visitAttribute(final Attribute attr) {
        }
    }
}
