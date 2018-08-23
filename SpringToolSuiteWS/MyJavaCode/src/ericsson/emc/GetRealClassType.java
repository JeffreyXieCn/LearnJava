/*
COPYRIGHT (C) ERICSSON INTERNET APPLICATIONS INC.

THIS SOFTWARE IS FURNISHED UNDER A LICENSE ONLY AND IS
PROPRIETARY TO ERICSSON INTERNET APPLICATIONS INC. IT MAY NOT BE COPIED
EXCEPT WITH THE PRIOR WRITTEN PERMISSION OF ERICSSON INTERNET APPLICATIONS
INC.  ANY COPY MUST INCLUDE THE ABOVE COPYRIGHT NOTICE AS
WELL AS THIS PARAGRAPH.  THIS SOFTWARE OR ANY OTHER COPIES
TO ANY OTHER PERSON OR ENTITY.
TITLE TO AND OWNERSHIP OF THIS SOFTWARE SHALL AT ALL
TIMES REMAIN WITH ERICSSON INTERNET APPLICATIONS INC.
*/

package ericsson.emc;

import java.util.ArrayList;

/**
 * @author ewenxie
 *
 */
public class GetRealClassType {

    /**
     * @param args
     */
    public static void main(String[] args) {
        ArrayList al = new ArrayList();
        al.add(new Integer(37));
        al.add(new String("Hello World"));
        al.add(new MyTestIOException());
        al.add(new Double(5.37));
        System.out.println(al.get(1).getClass().getCanonicalName());
        System.out.println(al.get(2).getClass().getCanonicalName());

    }

}
