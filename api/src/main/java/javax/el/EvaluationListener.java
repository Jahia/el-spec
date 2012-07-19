/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright (c) 2012 Oracle and/or its affiliates. All rights reserved.
 *
 * The contents of this file are subject to the terms of either the GNU
 * General Public License Version 2 only ("GPL") or the Common Development
 * and Distribution License("CDDL") (collectively, the "License").  You
 * may not use this file except in compliance with the License.  You can
 * obtain a copy of the License at
 * https://glassfish.dev.java.net/public/CDDL+GPL_1_1.html
 * or packager/legal/LICENSE.txt.  See the License for the specific
 * language governing permissions and limitations under the License.
 *
 * When distributing the software, include this License Header Notice in each
 * file and include the License file at packager/legal/LICENSE.txt.
 *
 * GPL Classpath Exception:
 * Oracle designates this particular file as subject to the "Classpath"
 * exception as provided by Oracle in the GPL Version 2 section of the License
 * file that accompanied this code.
 *
 * Modifications:
 * If applicable, add the following below the License Header, with the fields
 * enclosed by brackets [] replaced by your own identifying information:
 * "Portions Copyright [year] [name of copyright owner]"
 *
 * Contributor(s):
 * If you wish your version of this file to be governed by only the CDDL or
 * only the GPL Version 2, indicate your decision by adding "[Contributor]
 * elects to include this software in this distribution under the [CDDL or GPL
 * Version 2] license."  If you don't indicate a single choice of license, a
 * recipient has the option to distribute your version of this file under
 * either the CDDL, the GPL Version 2 or to extend the choice of license to
 * its licensees as provided above.  However, if you add GPL Version 2 code
 * and therefore, elected the GPL Version 2 license, then the option applies
 * only if the new code is made subject to such option by the copyright
 * holder.
 */

package javax.el;

/**
 * The listener interface for receiving notification when an
 * EL expression is evaluated.
 *
 * @since EL 3.0
 */
public abstract class EvaluationListener {

    /**
     * Receives notification before an EL expression is evaluated
     * @param context The ELContext
     * @param expression The EL expression string to be evaluated
     */
    public void beforeEvaluation(ELContext context, String expression) {
    }

    /**
     * Receives notification after an EL expression is evaluated
     * @param context The ELContext
     * @param expression The EL expression string to be evaluated
     */
    public void afterEvaluation(ELContext context, String expression) {
    }

    /**
     * Receives notification when the (base, property) pair is resolved
     * @param context The ELContext
     * @param base The base object
     * @param property The property object
     */
    public void propertyResolved(ELContext context, Object base, Object property) {
    }

    /**
     * Notifies the listeners before an EL expression is evaluated
     * @param context The ELContext
     * @param expr The EL expression string to be evaluated
     */
    public static void notifyBeforeEvaluation(ELContext context, String expr) {
        if (context.getEvaluationListeners() == null)
            return;
        for (EvaluationListener listener: context.getEvaluationListeners()) {
            listener.beforeEvaluation(context, expr);
        }
    }

    /**
     * Notifies the listeners after an EL expression is evaluated
     * @param context The ELContext
     * @param expr The EL expression string that has been evaluated
     */
    public static void notifyAfterEvaluation(ELContext context, String expr) {
        if (context.getEvaluationListeners() == null)
            return;
        for (EvaluationListener listener: context.getEvaluationListeners()) {
            listener.afterEvaluation(context, expr);
        }
    }

    /**
     * Notifies the listeners when the (base, property) pair is resolved
     * @param context The ELContext
     * @param base The base object
     * @param property The property Object
     */
    public static void notifyPropertyResolved(ELContext context,
                                              Object base, Object property) {
        if (context.getEvaluationListeners() == null)
            return;
        for (EvaluationListener listener: context.getEvaluationListeners()) {
            listener.propertyResolved(context, base, property);
        }
    }
}