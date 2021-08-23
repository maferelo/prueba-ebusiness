import { Employee } from './employee';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Sequence } from './sequence';
import { SequenceBackend } from './sequenceBackend';
import { environment } from 'src/environments/environment';

@Injectable({providedIn: 'root'})
export class EmployeeService {
  private apiServerUrl = environment.apiBaseUrl;

  constructor(private http: HttpClient){}

  public getEmployees(): Observable<Employee[]> {
    return this.http.get<Employee[]>(`${this.apiServerUrl}/employee/all`);
  }

  public getSequences(): Observable<SequenceBackend[]> {
    return this.http.get<SequenceBackend[]>(`${this.apiServerUrl}/verifiedADN/all`);
  }

  public addEmployee(employee: Employee): Observable<Employee> {
    return this.http.post<Employee>(`${this.apiServerUrl}/employee/add`, employee);
  }

  public addSequence(sequence: Sequence): Observable<Sequence> {
    console.log(sequence.id);
    sequence.id = sequence.id.replace(/(['"])/g, "");
    sequence.id = sequence.id.split(",");
    console.log(sequence.id);
    return this.http.post<Sequence>(`${this.apiServerUrl}/verifiedADN/add`, sequence);
  }

  public updateEmployee(employee: Employee): Observable<Employee> {
    return this.http.put<Employee>(`${this.apiServerUrl}/employee/update`, employee);
  }

  public deleteEmployee(employeeId: number): Observable<void> {
    return this.http.delete<void>(`${this.apiServerUrl}/employee/delete/${employeeId}`);
  }
}