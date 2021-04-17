package com.ucad.sn;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.Test;

class ArchTest {

    @Test
    void servicesAndRepositoriesShouldNotDependOnWebLayer() {
        JavaClasses importedClasses = new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importPackages("com.ucad.sn");

        noClasses()
            .that()
            .resideInAnyPackage("com.ucad.sn.service..")
            .or()
            .resideInAnyPackage("com.ucad.sn.repository..")
            .should()
            .dependOnClassesThat()
            .resideInAnyPackage("..com.ucad.sn.web..")
            .because("Services and repositories should not depend on web layer")
            .check(importedClasses);
    }
}
