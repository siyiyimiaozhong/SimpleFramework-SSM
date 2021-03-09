package org.simpleframework.aop.aspect;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.simpleframework.aop.PointcutLocator;

@Data
@AllArgsConstructor
public class AspectInfo {
    private int orderIndex;
    private DefaultAspect aspect;
    private PointcutLocator pointcutLocator;
}
