package com.kubadziworski.parser.parser;

import com.kubadziworski.parser.domain.Class;

/**
 * Created by jdziworski on 30.03.16.
 */
public interface Parser {
    Class parse(String code);
}
