package com.jhs.dynamictable.global.support.dto;

public record TableNameMetadata(
        String originName,
        String generatedName
) {

    public String selectName(String targetOriginName) {
        if (this.originName.equals(targetOriginName)) {
            return generatedName;
        }

        return this.originName;
    }

}
