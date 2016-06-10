Feature: ELB Health Check

  Scenario: Status requested
    When I request the application status
    Then a healthy status is returned
