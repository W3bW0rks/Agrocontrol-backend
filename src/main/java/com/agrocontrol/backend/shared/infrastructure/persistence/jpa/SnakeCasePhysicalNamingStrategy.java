package com.agrocontrol.backend.shared.infrastructure.persistence.jpa;

import org.hibernate.boot.model.naming.Identifier;
import org.hibernate.boot.model.naming.PhysicalNamingStrategy;
import org.hibernate.engine.jdbc.env.spi.JdbcEnvironment;

import static io.github.encryptorcode.pluralize.Pluralize.pluralize;
/**
 * SnakeCase Physical NamingStrategy
 * @summary
 * This class is used to convert the table names to snake case and pluralize them.
 * For example, if the entity name is "User", the table name will be "users".
 * @since 1.0
 * @see org.hibernate.boot.model.naming.PhysicalNamingStrategy
 */
public class SnakeCasePhysicalNamingStrategy implements PhysicalNamingStrategy {
    /**
     * Convert the Catalog Name to snake case
     * @param identifier Catalog Name
     * @param jdbcEnvironment JDBC Environment
     * @return Snake Case Catalog Name
     */
    @Override
    public Identifier toPhysicalCatalogName(Identifier identifier, JdbcEnvironment jdbcEnvironment) {
        return this.toSnakeCase(identifier);
    }
    @Override
    public Identifier toPhysicalSchemaName(Identifier identifier, JdbcEnvironment jdbcEnvironment) {
        return this.toSnakeCase(identifier);
    }
    @Override
    public Identifier toPhysicalTableName(Identifier identifier, JdbcEnvironment jdbcEnvironment) {
        return this.toSnakeCase(this.toPlural(identifier));
    }
    @Override
    public Identifier toPhysicalSequenceName(Identifier identifier, JdbcEnvironment jdbcEnvironment) {
        return this.toSnakeCase(identifier);
    }
    @Override
    public Identifier toPhysicalColumnName(Identifier identifier, JdbcEnvironment jdbcEnvironment) {
        return this.toSnakeCase(identifier);
    }
    private Identifier toSnakeCase(final Identifier identifier) {
        if (identifier == null) return null;
        final String regex = "([a-z])([A-Z])";
        final String replacement = "$1_$2";
        final String newName = identifier.getText()
                .replaceAll(regex, replacement)
                .toLowerCase();
        return Identifier.toIdentifier(newName);
    }
    private Identifier toPlural(final Identifier identifier) {
        final String newName = pluralize(identifier.getText());
        return Identifier.toIdentifier(newName);
    }
}
