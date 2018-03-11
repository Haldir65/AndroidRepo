package com.johnDoe

import org.gradle.api.Plugin
import org.gradle.api.Project

class MainDexPlugin implements Plugin<Project> {


    class Book {
        final String name
        File sourceFile

        Book(String name) {
            this.name = name
        }
    }


    @Override
    void apply(Project project) {

        // Create a container of Book instances
        def books = project.container(Book)
        books.all {
            sourceFile = project.file("src/docs/$name")
        }
        // Add the container as an extension object
        project.extensions.books = books

    }
}