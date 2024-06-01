package com.example.demo.product.repository.record;

import com.example.demo.category.vo.CategoryId;
import com.example.demo.product.vo.*;

public class ProductRecordFactory {

    public static ProductRecord createInsertProductRecord(
            ProductCode productCode,
            ProductName name,
            CategoryId categoryId,
            Price price,
            Description description
    ) {
        return new ProductRecord(
                0,
                productCode.value(),
                name.value(),
                categoryId.value(),
                "",
                price.value(),
                "",
                description.value(),
                null,
                null
        );
    }

    public static ProductRecord createUpdateProductRecord(
            ProductId id,
            ProductCode productCode,
            ProductName name,
            CategoryId categoryId,
            Price price,
            Description description
    ) {
        return new ProductRecord(
                id.value(),
                productCode.value(),
                name.value(),
                categoryId.value(),
                "",
                price.value(),
                "",
                description.value(),
                null,
                null
        );
    }
}
