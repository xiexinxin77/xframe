package com.xiexinxin.frame.business.atom.config;

/**
 * author: xiexx
 * data: 2019/11/17
 * time: 17:08
 */
public class AtomConfig {
    private String atomCode;
    private String className;
    private String classMethod;
    private String atomName;
    private String atomDescription;

    public String getAtomCode() {
        return atomCode;
    }

    public void setAtomCode(String atomCode) {
        this.atomCode = atomCode;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getClassMethod() {
        return classMethod;
    }

    public void setClassMethod(String classMethod) {
        this.classMethod = classMethod;
    }

    public String getAtomName() {
        return atomName;
    }

    public void setAtomName(String atomName) {
        this.atomName = atomName;
    }

    public String getAtomDescription() {
        return atomDescription;
    }

    public void setAtomDescription(String atomDescription) {
        this.atomDescription = atomDescription;
    }

    @Override
    public String toString() {
        return "AtomConfig{" +
                "atomCode='" + atomCode + '\'' +
                ", className='" + className + '\'' +
                ", methodName='" + classMethod + '\'' +
                ", atomName='" + atomName + '\'' +
                ", atomDescription='" + atomDescription + '\'' +
                '}';
    }
}
