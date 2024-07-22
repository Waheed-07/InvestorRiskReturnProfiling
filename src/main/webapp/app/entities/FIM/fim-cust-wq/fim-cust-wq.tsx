import React, { useState, useEffect } from 'react';
import { Link, useLocation, useNavigate } from 'react-router-dom';
import { Button, Table } from 'reactstrap';
import { Translate, TextFormat, getSortState } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faSort, faSortUp, faSortDown } from '@fortawesome/free-solid-svg-icons';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { ASC, DESC, SORT } from 'app/shared/util/pagination.constants';
import { overrideSortStateWithQueryParams } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntities } from './fim-cust-wq.reducer';

export const FimCustWq = () => {
  const dispatch = useAppDispatch();

  const pageLocation = useLocation();
  const navigate = useNavigate();

  const [sortState, setSortState] = useState(overrideSortStateWithQueryParams(getSortState(pageLocation, 'id'), pageLocation.search));

  const fimCustWqList = useAppSelector(state => state.fim.fimCustWq.entities);
  const loading = useAppSelector(state => state.fim.fimCustWq.loading);

  const getAllEntities = () => {
    dispatch(
      getEntities({
        sort: `${sortState.sort},${sortState.order}`,
      }),
    );
  };

  const sortEntities = () => {
    getAllEntities();
    const endURL = `?sort=${sortState.sort},${sortState.order}`;
    if (pageLocation.search !== endURL) {
      navigate(`${pageLocation.pathname}${endURL}`);
    }
  };

  useEffect(() => {
    sortEntities();
  }, [sortState.order, sortState.sort]);

  const sort = p => () => {
    setSortState({
      ...sortState,
      order: sortState.order === ASC ? DESC : ASC,
      sort: p,
    });
  };

  const handleSyncList = () => {
    sortEntities();
  };

  const getSortIconByFieldName = (fieldName: string) => {
    const sortFieldName = sortState.sort;
    const order = sortState.order;
    if (sortFieldName !== fieldName) {
      return faSort;
    } else {
      return order === ASC ? faSortUp : faSortDown;
    }
  };

  return (
    <div>
      <h2 id="fim-cust-wq-heading" data-cy="FimCustWqHeading">
        <Translate contentKey="fimApp.fimFimCustWq.home.title">Fim Cust Wqs</Translate>
        <div className="d-flex justify-content-end">
          <Button className="me-2" color="info" onClick={handleSyncList} disabled={loading}>
            <FontAwesomeIcon icon="sync" spin={loading} />{' '}
            <Translate contentKey="fimApp.fimFimCustWq.home.refreshListLabel">Refresh List</Translate>
          </Button>
          <Link to="/fim/fim-cust-wq/new" className="btn btn-primary jh-create-entity" id="jh-create-entity" data-cy="entityCreateButton">
            <FontAwesomeIcon icon="plus" />
            &nbsp;
            <Translate contentKey="fimApp.fimFimCustWq.home.createLabel">Create new Fim Cust Wq</Translate>
          </Link>
        </div>
      </h2>
      <div className="table-responsive">
        {fimCustWqList && fimCustWqList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th className="hand" onClick={sort('id')}>
                  <Translate contentKey="fimApp.fimFimCustWq.id">ID</Translate> <FontAwesomeIcon icon={getSortIconByFieldName('id')} />
                </th>
                <th className="hand" onClick={sort('custId')}>
                  <Translate contentKey="fimApp.fimFimCustWq.custId">Cust Id</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('custId')} />
                </th>
                <th className="hand" onClick={sort('clientId')}>
                  <Translate contentKey="fimApp.fimFimCustWq.clientId">Client Id</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('clientId')} />
                </th>
                <th className="hand" onClick={sort('idType')}>
                  <Translate contentKey="fimApp.fimFimCustWq.idType">Id Type</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('idType')} />
                </th>
                <th className="hand" onClick={sort('ctryCode')}>
                  <Translate contentKey="fimApp.fimFimCustWq.ctryCode">Ctry Code</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('ctryCode')} />
                </th>
                <th className="hand" onClick={sort('createdBy')}>
                  <Translate contentKey="fimApp.fimFimCustWq.createdBy">Created By</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('createdBy')} />
                </th>
                <th className="hand" onClick={sort('createdTs')}>
                  <Translate contentKey="fimApp.fimFimCustWq.createdTs">Created Ts</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('createdTs')} />
                </th>
                <th className="hand" onClick={sort('updatedBy')}>
                  <Translate contentKey="fimApp.fimFimCustWq.updatedBy">Updated By</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('updatedBy')} />
                </th>
                <th className="hand" onClick={sort('updatedTs')}>
                  <Translate contentKey="fimApp.fimFimCustWq.updatedTs">Updated Ts</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('updatedTs')} />
                </th>
                <th className="hand" onClick={sort('recordStatus')}>
                  <Translate contentKey="fimApp.fimFimCustWq.recordStatus">Record Status</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('recordStatus')} />
                </th>
                <th className="hand" onClick={sort('uploadRemark')}>
                  <Translate contentKey="fimApp.fimFimCustWq.uploadRemark">Upload Remark</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('uploadRemark')} />
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {fimCustWqList.map((fimCustWq, i) => (
                <tr key={`entity-${i}`} data-cy="entityTable">
                  <td>
                    <Button tag={Link} to={`/fim/fim-cust-wq/${fimCustWq.id}`} color="link" size="sm">
                      {fimCustWq.id}
                    </Button>
                  </td>
                  <td>{fimCustWq.custId}</td>
                  <td>{fimCustWq.clientId}</td>
                  <td>{fimCustWq.idType}</td>
                  <td>{fimCustWq.ctryCode}</td>
                  <td>{fimCustWq.createdBy}</td>
                  <td>{fimCustWq.createdTs ? <TextFormat type="date" value={fimCustWq.createdTs} format={APP_DATE_FORMAT} /> : null}</td>
                  <td>{fimCustWq.updatedBy}</td>
                  <td>{fimCustWq.updatedTs ? <TextFormat type="date" value={fimCustWq.updatedTs} format={APP_DATE_FORMAT} /> : null}</td>
                  <td>{fimCustWq.recordStatus}</td>
                  <td>{fimCustWq.uploadRemark}</td>
                  <td className="text-end">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`/fim/fim-cust-wq/${fimCustWq.id}`} color="info" size="sm" data-cy="entityDetailsButton">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`/fim/fim-cust-wq/${fimCustWq.id}/edit`} color="primary" size="sm" data-cy="entityEditButton">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button
                        onClick={() => (window.location.href = `/fim/fim-cust-wq/${fimCustWq.id}/delete`)}
                        color="danger"
                        size="sm"
                        data-cy="entityDeleteButton"
                      >
                        <FontAwesomeIcon icon="trash" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.delete">Delete</Translate>
                        </span>
                      </Button>
                    </div>
                  </td>
                </tr>
              ))}
            </tbody>
          </Table>
        ) : (
          !loading && (
            <div className="alert alert-warning">
              <Translate contentKey="fimApp.fimFimCustWq.home.notFound">No Fim Cust Wqs found</Translate>
            </div>
          )
        )}
      </div>
    </div>
  );
};

export default FimCustWq;
