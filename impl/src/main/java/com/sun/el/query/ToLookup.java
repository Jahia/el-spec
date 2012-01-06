package com.sun.el.query;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Comparator;

import javax.el.ELContext;
import javax.el.LambdaExpression;
import javax.el.Grouping;

class ToLookup extends QueryOperator {
    @Override
    public Map<Object, ArrayListGrouping<Object,Object>> invoke(
                                   final ELContext context,
                                   final Iterable<Object> base,
                                   final Object[] params) {
        final LambdaExpression keySelector = getLambda("toLookup", params, 0);
        final LambdaExpression elementSelector =
                  getLambda("toLookup", params, 1, true);
        int indexC = 1;
        if (elementSelector != null) {
            indexC++;
        }
        final Comparator cmp = getComparator("toLookup", params, indexC, true);

        Map<Object, ArrayListGrouping<Object,Object>> map =
                    new HashMap<Object, ArrayListGrouping<Object,Object>>();

        for (Object element: base) {
            Object key = keySelector.invoke(context, element);
            Object dest = key;
            if (elementSelector != null) {
                dest = elementSelector.invoke(context, element);
            }
            addToGroup(map, key, dest);
        }
        return map;
    }

    private void addToGroup(Map<Object, ArrayListGrouping<Object,Object>> map,
                            Object key, Object value) {
        if (key == null || value == null) {
            return;
        }
        for (ArrayListGrouping<Object, Object> group: map.values()) {
            if (key.equals(group.getKey())) {
                group.add(value);
                return;
            }
        }
        ArrayListGrouping<Object, Object> g =
                    new ArrayListGrouping<Object,Object>(key);
        g.add(value);
        map.put(key, g);
        return;
    }
}
